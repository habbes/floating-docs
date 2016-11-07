(ns floatingdocs.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route])
  (:use [floatingdocs.routes]
    [floatingdocs.middleware]))

(def api-base "/api")

(defroutes app-routes
  (GET "/" [] "Hello World")
  (context api-base []  api-routes)
  (route/not-found "Not Found"))


    
  

(def app
  (-> app-routes (wrap-api)))
