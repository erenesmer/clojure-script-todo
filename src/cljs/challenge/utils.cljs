(ns challenge.utils
  (:require
    [day8.re-frame.http-fx]
    [ajax.core :as ajax]))

(defonce api-url "http://localhost:8000")

(defn get-request-map [method url on-success on-failure params]
  (let [request-map
        {
         :method          method
         :uri             (str api-url url)
         :format          (ajax/url-request-format)
         :response-format (ajax/json-response-format {:keywords? true})
         :on-success      (if (vector? on-success) on-success [on-success])
         :on-failure      (if (vector? on-failure) on-failure [on-failure])}]
    (if (not-empty params) (merge request-map {:params params :format (ajax/json-request-format)}) request-map)
    )
  )
