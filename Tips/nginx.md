### install 
http://nginx.org/en/download.html

安装第三方module需要用源码make:
wget http://nginx.org/download/nginx-1.8.1.tar.gz 
tar -zxvf nginx-1.8.1.tar.gz 
cd nginx-1.8.1 
./configure --prefix=/usr/local/nginx  --add-module=../nginx-rtmp-module  --with-http_ssl_module   
make && make install

中间可能报错, 缺依赖
yum -y install gcc gcc-c++ autoconf automake make
yum -y install pcre-devel
yum -y install openssl openssl-devel


参考: https://www.cnblogs.com/suiyuewuxin/p/7256972.html
https://www.cnblogs.com/jpfss/p/9694842.html
https://blog.csdn.net/hfsu0419/article/details/7190152
https://blog.csdn.net/Ricardo18/article/details/89359623

## webdav
./configure --with-http_v2_module --with-http_ssl_module --with-http_dav_module --prefix=/home/beyond/software/bin/nginx/nginx --add-module=/home/beyond/software/bin/nginx/nginx-module/headers-more-nginx-module-0.33 --add-module=/home/beyond/software/bin/nginx/nginx-module/nginx-dav-ext-module-3.0.0
 

```

#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    # webdav
    dav_ext_lock_zone zone=foo:10m;
    server {
        listen       8080;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root /home/beyond/software/bin/nginx/nginx/webdav;
            dav_methods PUT DELETE MKCOL COPY MOVE;
            dav_ext_methods PROPFIND OPTIONS;
            create_full_put_path on;
            dav_access user:rw group:r all:r;
            auth_basic "Authorized Users Only";
            auth_basic_user_file /home/beyond/software/bin/nginx/nginx/conf/.davpasswd;
            dav_ext_lock zone=foo;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }


    server {
        listen       8089;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root   html;
            index  index.html index.htm;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}

```


## .davpasswd
echo "admin:$(openssl passwd 123456)" > /home/beyond/software/bin/nginx/nginx/conf/.davpasswd

