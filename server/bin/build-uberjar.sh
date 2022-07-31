mkdir -p build
clojure -X:uberjar :jar build/clojure-dapp.jar :main-class is.mad.clojure-dapp.server
