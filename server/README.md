## Clojure DApp server

```
❯ curl "https://apps.mad.is/clojure-dapp/hello?this=too"
Hello Clojure server world. You sent: this=too path: /clojure-dapp/hello
```

### Developing, test & deploy

1. Run app: `./bin/start-server.sh`
2. Run tests: `./bin/run-tests.sh`
3. Build uberjar for deployment: `./bin/build-uberjar.sh`

### Running on server (Systemd)

Under `/home/madis/.config/systemd/user/clojure-dapp.service`

```systemd
[Unit]
Description=Clojure DApp Web application
After=syslog.target network.target

[Service]
User=madis
WorkingDirectory=/home/madis/www/apps.mad.is
ExecStart=/usr/bin/java -jar /home/madis/www/apps.mad.is/clojure-dapp.jar
ExecStop=/bin/kill -15 $MAINPID

[Install]
WantedBy=multi-user.target
```

Then to enable the service run: `systemctl --user enable clojure-dapp.service`
  - to keep it from getting killed after user logs out: `loginctl enable-linger username`

Other useful systemd commands:
1. View status: `systemctl --user status clojure-dapp`
2. Add user service: `systemctl --user enable clojure-dapp.service`
3. Reload: `systemctl --user daemon-reload`
4. Start: `systemctl --user start clojure-dapp.service`
