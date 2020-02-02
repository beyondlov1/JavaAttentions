
var host = window.location.host;
// host = "localhost:8080"

var userId = ""+random(0,1000000);
var ws = new WebSocket("ws://"+host+"/bingo?userId="+userId);

ws.onopen = function(evt) {
    console.log("Connection open ...");
};

ws.onmessage = function(evt) {
    console.log("Received Message: " + evt.data);
    var data = JSON.parse(evt.data);
    if (data.userId && data.userId != userId && data.msg) {
        var pos = JSON.parse(data.msg);
        var nextTurnType = pos.type;
        if(writeResult(pos,nextTurnType)){
            draw(canvas,pos,nextTurnType);
            if(judge(result)){
                if(nextTurnType == $("#myType").val()){
                   alert("恭喜你赢了");
                }else{
                   alert("你输了");
                }
               clear(canvas);
            }else{
                if (isDrew(result)) {
                    alert("平局");
                    clear(canvas);
                }
            }
        }else{
            console.log("please wait");
        }
    }
};

ws.onclose = function(evt) {
    console.log("Connection closed.");
};

function random(min,max){
    return parseInt(Math.random()*(max-min+1)+min,10);
}

function createMessage(pos,type){
    pos.type=type;

    var toUserId = $("#toUserId").val();
    var userName = $("#userName").val();

    var fullMessage = {};
    fullMessage.userId = userId;
    fullMessage.toUserId = toUserId;
    fullMessage.userName = userName;
    fullMessage.msg=JSON.stringify(pos);
    return JSON.stringify(fullMessage);
}

function sendMessage(fullMessage){
    ws.send(fullMessage);
}


var userWs = new WebSocket("ws://"+host+"/users?userId="+userId);

userWs.onopen = function(evt) {
    console.log("Connection open ...");
};

userWs.onmessage = function(evt) {
    console.log("Received Message: " + evt.data);
    var data = JSON.parse(evt.data);
    var $userContainer = $("#userContainer");
    $userContainer.empty();

    var myUser = null;
    data.forEach(user => {
        if(user!=null && user.userId ==  window.userId){
            myUser = user;
        }
    });

    var isToUserExist = false;
    for (x in data) {
        if (data[x] != null && data[x]["userId"] == window.userId) {
            continue;
        }
        var userName = "";
        var userId = "";
        var inviteStatus = {};
        if (data[x] != null) {
            userName = data[x]["userName"];
            userId = data[x]["userId"];
            inviteStatus = data[x]["inviteStatus"];
        }
        if (inviteStatus!=null && inviteStatus.status == 2) {
            $userContainer.append($("<p class='userListItem' style='color:gray;' onclick='alertPlaying(this,"+JSON.stringify(data[x])+")'>"+userId+": "+userName+"</p>"))
        }else{ 
            $userContainer.append($("<p class='userListItem' style='cursor:pointer;' onclick='chooseUserId(this,"+JSON.stringify(data[x])+")'>"+userId+": "+userName+"</p>"))
        }

        if(userId == $("#toUserId").val()){
            isToUserExist = true;
        }
    }

    if ( $("#toUserId").val() != "" && !isToUserExist) {
        sendGoodByeMessage(createMyGoodByeMessage());
        alert("对方断开");
        $("#toUserId").val("");
        $("#myType").val("");
        clear(canvas);
    }
};

function alertPlaying(target,data){
    alert("用户正在游戏中");
}

userWs.onclose = function(evt) {
    console.log("Connection closed.");
};

function fetchUserIds(){
    userWs.send(JSON.stringify({userId:userId,userName:$("#userName").val()}));
    setTimeout(fetchUserIds,1000)
}
setTimeout(fetchUserIds,1000);

function chooseUserId(target,data) {
    if($("#myType").val() != "" &&  $("#myType").val() != null){
        if (confirm("你当前正在游戏中, 是否断开")) {
            sendGoodByeMessage(createGoodByeMessage());
            $("#toUserId").val("");
            $("#myType").val("");
            clear(canvas);
        }
        return;
    }
    $("#toUserId").val($(target)[0].textContent);
    $("#toUserId").val(data["userId"]);
    sendInviteMessage(createRoundMessage());
}




var inviteWs = new WebSocket("ws://"+host+"/invite?userId="+userId);

inviteWs.onopen = function(evt) {
    console.log("Connection open ...");
};

inviteWs.onmessage = function(evt) {
    console.log("Received Message: " + evt.data);
    var data = JSON.parse(evt.data);
    if (data.userId && data.msg ) {
        var inviteStatuses = data.msg;
        if(data.success){
            inviteStatuses.forEach(inviteStatus => {
                if(inviteStatus.userId != userId){
                    $("#toUserId").val(inviteStatus.userId);
                    $("#myType").val(inviteStatus.type);
                }
            });
            clear(canvas);
            showInviteResultMessage(data);
        }else{
            if (inviteStatuses.length == 1) {
                acceptInvite(data);
            }else{
                console.log("invite fail");
            }
        }
    }
};

function showInviteResultMessage(inviteMessage){
    $("#gameMessage").text("正在与"+inviteMessage.userName +"对战...");
}

function acceptInvite(inviateMessage){
    inviateMessage.msg.push({userId:userId,status:1});
    sendInviteMessage(JSON.stringify(inviateMessage));
}

inviteWs.onclose = function(evt) {
    console.log("Connection closed.");
};

function createRoundMessage(){
    var toUserId = $("#toUserId").val();
    var userName = $("#userName").val();

    var fullMessage = {};
    fullMessage.userId = userId;
    fullMessage.toUserId = toUserId;
    fullMessage.userName = userName;
    var msg = [];
    msg.push({userId:userId,status:1}); // inviting
    fullMessage.msg=msg;
    return JSON.stringify(fullMessage);
}

function sendInviteMessage(fullMessage){
    inviteWs.send(fullMessage);
}





var goodByeWs = new WebSocket("ws://"+host+"/goodBye?userId="+userId);

goodByeWs.onopen = function(evt) {
    console.log("Connection open ...");
};

goodByeWs.onmessage = function(evt) {
    console.log("Received Message: " + evt.data);
    var data = JSON.parse(evt.data);
    if (data.userId ) {
        alert("对方断开");
        $("#toUserId").val("");
        $("#myType").val("");
        clear(canvas);
    }
};

goodByeWs.onclose = function(evt) {
    console.log("Connection closed.");
};

function createGoodByeMessage(){
    var toUserId = $("#toUserId").val();
    var userName = $("#userName").val();

    var fullMessage = {};
    fullMessage.userId = userId;
    fullMessage.toUserId = toUserId;
    fullMessage.userName = userName;
    return JSON.stringify(fullMessage);
}


function createMyGoodByeMessage(){
    var userName = $("#userName").val();

    var fullMessage = {};
    fullMessage.userId = userId;
    fullMessage.userName = userName;
    return JSON.stringify(fullMessage);
}

function sendGoodByeMessage(fullMessage){
    goodByeWs.send(fullMessage);
}

