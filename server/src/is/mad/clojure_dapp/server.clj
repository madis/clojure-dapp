(ns is.mad.clojure-dapp.server
  (:gen-class)
  (:require [org.httpkit.server :as server]))

(defn handler [req]
  {:status 200
   :headers {}
   :body (str "Hello Clojure server world. "
              "You sent: " (:query-string req) " path: " (:uri req) "\n")})

(defn create-server [port]
  (server/run-server handler {:port port}))

(defonce ^:private api-server (atom nil))

(defn start-server
  ([] (start-server 8080))
  ([port] (reset! api-server (server/run-server #'handler {:port port}))))

(defn stop-server
  "Gracefully shut down the server, waiting 100 ms"
  []
  (when-not (nil? @api-server)
    (println "Gracefully stopping api-server")
    (@api-server :timeout 100)
    (reset! api-server nil)
    (println "Done stopping api-server")))

(.addShutdownHook (Runtime/getRuntime)
                  (Thread. (fn []
                             (println "Shutting down...")
                             (stop-server)
                             (println "All done. Exiting"))))

(defn -main [& args]
  (println "Starting server")
  (start-server))
