(ns challenge.api
  (:require
    [day8.re-frame.http-fx]
    [ajax.core :as ajax]))

(defonce api-url "http://localhost:8000")

(defn make-request [type url on-success on-failure params]
  {:http-xhrio
   {
    :method          type
    :uri             (str api-url url)
    :format          (ajax/json-request-format)
    :response-format (ajax/json-response-format {:keywords? true})
    :params          params
    :on-success      (if (vector? on-success) on-success [on-success])
    :on-failure      (if (vector? on-failure) :common-on-failure [:common-on-failure])}})


;(defn make-request
;      [method url on-success on-failure]
;      {:http-xhrio {:method          method
;                    :uri             (str api-url url)
;                    :format          (json-request-format)
;                    :response-format (json-response-format)
;                    {:keywords? true}
;                                     :on-success
;                    (if (vector? on-success) on-success [on-success])
;                                     :on-failure
;                    (if (vector? on-failure) on-failure [on-failure])
;                    }})
