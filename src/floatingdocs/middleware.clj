(ns floatingdocs.middleware
    (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.util.response :as response]))

(defn wrap-json-content-type 
    "Adds the application/json content type header to the response"
    [handler]
    (fn [request]
        (response/content-type (handler request) "application/json")))

(defn wrap-api 
    "Wraps the handler with middleware suitable for a REST API"
    [handler]
    (-> handler
        (wrap-defaults api-defaults)
        (wrap-json-content-type)
        (wrap-json-response)
        (wrap-cors)))

(defn wrap-api-or-site-defaults [handler api-base]
    "If the uri starts with [[api-base]] then the handler will be wrapped
    with middleware suitable for APIs, otherwise it will be wrapped with
    middlware suitable for websites"
    (fn [request]
        (if (clojure.string/starts-with? (:uri request) api-base) 
            ((wrap-api handler) request)
            ((wrap-defaults handler site-defaults) request))))