#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <sys/epoll.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>

static int running;

void 
intsignhandler(){
    running = 0;    
}

int main(int argc, char const *argv[])
{
    setbuf(stdout,NULL);
    signal(SIGINT, intsignhandler);
    running = 1;
    int sockfd = socket(AF_INET,SOCK_STREAM,0);
    if(sockfd < 0){
		perror("socket create fail");
		exit(1);
    }
    // 设置套接字选项避免地址使用错误
    int on=1;
    if((setsockopt(sockfd,SOL_SOCKET,SO_REUSEADDR,&on,sizeof(on)))<0)
    {
        perror("setsockopt failed");
        exit(EXIT_FAILURE);
    }
    struct sockaddr_in addr;
    addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    addr.sin_family = AF_INET;
	addr.sin_port = htons(atoi("8099"));
    int success = bind(sockfd, (struct sockaddr*)&addr,sizeof(addr));
    if(success < 0){
        perror("bind error");
        close(sockfd);
        return 1;
    }
    int lsuccess = listen(sockfd, 20);
    if(lsuccess < 0){
        perror("listen error");
        close(sockfd);
        return 1;
    }
    int epfd = epoll_create(1024);
    if(epfd < 0){
        perror("epoll create error");
        close(epfd);
        return 1;
    }
    struct epoll_event ev;
    ev.data.fd = sockfd;
    ev.events = EPOLLIN;
    epoll_ctl(epfd, EPOLL_CTL_ADD, sockfd, &ev);
    struct epoll_event evs[1024];
    while (1)
    {
        int n = epoll_wait(epfd, evs, 1024, 2000);
        if(!running) break;
        int i ;
        for(i = 0; i<n;i++){
            struct epoll_event e = evs[i];
            if(evs[i].events & EPOLLIN){
                if(e.data.fd == sockfd){
                    struct sockaddr_in caddr;
                    memset(&caddr, 0, sizeof(caddr));
                    socklen_t acceptsize = sizeof(struct sockaddr);
                    int fd = accept(sockfd,(struct sockaddr*)&caddr,&acceptsize);
                    if(fd < 0){
                        perror("accept error");
                        return 1;
                    }
                    struct epoll_event ce;
                    ce.data.fd = fd;
                    ce.events = EPOLLIN;
                    epoll_ctl(epfd, EPOLL_CTL_ADD, fd, &ce);
                }else{
                    int cfd = e.data.fd;

                    int bufsize = 10;
                    char buf[bufsize];
                    bzero(buf, sizeof(buf));
                    int n = read(evs[i].data.fd,buf,bufsize-1);
                    if(n > 0 && n < bufsize-1){
                        printf("Recv<fd-%d>:%s\n",evs[i].data.fd,buf);
                    }else if(n == 0){
                        struct epoll_event de;
                        de.data.fd = cfd;
                        de.events = EPOLLIN;
                        epoll_ctl(epfd, EPOLL_CTL_DEL, cfd, &de);
                        printf("%d closed", cfd);
                    }else{
                        buf[-1] = '\0';
                        printf("Recv<fd-%d>:%s\n",evs[i].data.fd,buf);
                        printf("%s", "too large");
                    }
                }
            }
        }
    }
    printf("close sockfd");
    close(sockfd);
    return 0;
}
