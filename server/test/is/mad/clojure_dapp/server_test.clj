(ns is.mad.clojure-dapp.server-test
  (:gen-class)
  (:require [clojure.test :refer [deftest is testing]]
            [is.mad.clojure-dapp.server :as cds]))

(deftest handler-test
  (testing "Respond to events"
    (is (= 200 (:status (cds/handler {}))))))
