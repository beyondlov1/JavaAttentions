$(function(){
    $("#myUserId").val(userId);
    $("#userName").val(userId);
    initResult();
    drawBoard(canvas);

    $('#dlg').dialog({
        width: 400,
        height: 120,
        closed: true,
        modal: true
    });
    if ($('#userName').val() == userId || $('#userName').val() =="") {
        $('#dlg').dialog("open");
    }
    $("#dialogUserName").on("keydown",function(e){
        if(e.keyCode == 13){
            $('#userName').val($("#dialogUserName").val());
            $('#dlg').dialog("close");
        }
    })
})


    var result = [];

    const canvas = document.getElementById("board");

    const unitLength = 100;

    function drawBoard(canvas){
        const ctx = canvas.getContext('2d');
        ctx.translate(100,100);

        if ($("#toUserId").val() == "" && $("#myType").val() == "") {
            drawText(ctx,'请选择一个对手');
        }

        ctx.strokeRect(0, 0, 3*unitLength, 3*unitLength);
    
        ctx.beginPath();
        ctx.moveTo(0,unitLength);
        ctx.lineTo(3*unitLength,unitLength);
    
        ctx.moveTo(0,2*unitLength);
        ctx.lineTo(3*unitLength,2*unitLength);
    
        ctx.moveTo(unitLength,0);
        ctx.lineTo(unitLength,3*unitLength);
    
        ctx.moveTo(2*unitLength,0);
        ctx.lineTo(2*unitLength,3*unitLength);
        ctx.closePath();
        ctx.stroke();
    }

    function drawText(ctx,text){
        ctx.font = "bold 40px '字体','字体','微软雅黑','宋体'"; //设置字体
        ctx.textAlign='center';
        ctx.textBaseline='middle';
        ctx.fillText(text, 150,150); //设置文本内容
    }

    function drawCircle(canvas, pos){
        const ctx = canvas.getContext('2d');

        var x = pos.x * unitLength + unitLength/2;
        var y = pos.y * unitLength+ unitLength/2;
        var  r = unitLength/2;
        ctx.beginPath();
        ctx.arc(x, y, r, 0, Math.PI*2, false);
        ctx.stroke();
    }

    function drawCross(canvas,pos){
        const ctx = canvas.getContext('2d');

        var left = pos.x * unitLength;
        var right = pos.x * unitLength + unitLength;
        var top = pos.y * unitLength;
        var bottom = pos.y * unitLength + unitLength;

        ctx.beginPath();
        ctx.moveTo(left,top);
        ctx.lineTo(right,bottom);

        ctx.moveTo(right,top);
        ctx.lineTo(left,bottom);
        ctx.closePath();
        ctx.stroke();
    }

    //获取鼠标指针坐标
    function getMousePos (canvas, evt) {
        var rect = canvas.getBoundingClientRect();
        return {
            x: evt.clientX - rect.left,
            y: evt.clientY - rect.top
        };
    }

    canvas.addEventListener("click", function(evt){
        var mousePos = getMousePos(canvas, evt);
        var pos = getPos(mousePos);
        if(checkPos(pos)){
            if ($("#toUserId").val() == "") {
                alert("点击下方一个名字进行对战");
                return;
            }
            var nextTurnType = getNextTurnType(result);
            var myType = $("#myType").val();
            if(myType != nextTurnType){
                console.log("not your turn");
                alert("等对方下子");
                return;
            }
            sendToServer(pos,nextTurnType);
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
    },false);

    function isDrew(result){
        for (let i = 0; i < result.length; i++) {
            const lines = result[i];
            for (let j = 0; j < lines.length; j++) {
                const type = lines[j];
                if (type==0) {
                    return false;
                }
            }
        }
        return true;
    }

    function sendToServer(pos,type){
        sendMessage(createMessage(pos,type));
    }

    function clear(canvas){
        const ctx = canvas.getContext('2d');
        ctx.translate(-100,-100);
        ctx.clearRect(0,0,canvas.width,canvas.height);  
        drawBoard(canvas);
        initResult();
    }

    function draw(canvas,pos,type){
        if(type == 1){
            drawCircle(canvas,pos);
        }
        if(type == 4){
            drawCross(canvas,pos);
        }
    }

    function getPos(mousePos){
        var mousePosX = mousePos.x - 100;
        var mousePosY = mousePos.y - 100;
        return {
            x:Math.floor(mousePosX/100),
            y:Math.floor(mousePosY/100),
        }
    }

    function getNextTurnType(result){
        var types = {};
        result.forEach(lines => {
            lines.forEach(type => {
                var key = type+"";
                if(types[key]){
                    types[key] = types[key] + 1;
                }else{
                    types[key] = 1;
                }
            });
        });
        if(!types.hasOwnProperty("1")){
            return 1;
        }
        if(!types.hasOwnProperty("4")){
            return 4;
        }
        if(types.hasOwnProperty("1") && types.hasOwnProperty("4")){
            if(types["1"] > types["4"]){
                return 4;
            }else{
                return 1;
            }
        }
    }

    function checkPos(pos){
        if(pos.x>2 || pos.y>2 || pos.x<0 || pos.y<0){
            return false;
        }else{
            return true;
        }
    }
    
    function judge(result){

        var sum = 0;
        for (let i = 0; i < result.length; i++) {
            sum = 0;
            const lines = result[i];
            for (let j = 0; j < lines.length; j++) {
                const type = lines[j];
                sum = sum + type;
            }
            if(sum == 3 || sum == 12){
                return true;
            }
        }

        sum = 0;
        for (let i = 0; i < result.length; i++) {
            sum = 0;
            const lines = result[i];
            for (let j = 0; j < lines.length; j++) {
                const type = result[j][i];
                sum = sum + type;
            }
            if(sum == 3 || sum == 12){
                return true;
            }
        }

        sum = 0;
        for (let i = 0; i < result.length; i++) {
            const lines = result[i];
            for (let j = 0; j < lines.length; j++) {
                if(i == j){
                    const type = result[i][j];
                    sum = sum + type;
                }
            }
            if(sum == 3 || sum == 12){
                return true;
            }
        }

        sum = 0;
        for (let i = 0; i < result.length; i++) {
            const lines = result[i];
            for (let j = 0; j < lines.length; j++) {
                if(i == result.length - j -1){
                    const type = result[i][j];
                    sum = sum + type;
                }
            }
            if(sum == 3 || sum == 12){
                return true;
            }
        }
    }

    function writeResult(pos,type){
        if(result[pos.y][pos.x] == 0){
            result[pos.y][pos.x]= type;
            return true;
        }else{
            return false;
        }
    }

    function initResult(){
        result = [[
            0,0,0
        ],[
            0,0,0
        ],[
            0,0,0
        ]];
    }


