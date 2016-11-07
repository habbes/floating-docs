(ns floatingdocs.routes
  (:use compojure.core))

(defroutes page-routes
    (GET "/" [] '({:title "Page 1" :body "Content 1"} {:title "Page 2" :body "Content 2"})))


(defroutes api-routes
  (context "/pages" []
    page-routes))

