(ns challenge.app.views
  (:require
   [re-frame.core :as re-frame]
   [challenge.app.subs :as subs]
   ))


(defn app []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 (str "Hello from " @name ". This is the Home Page.")]

     [:div
      [:a {:href "#/about"}
       "go to About Page"]]
     ]))

;; main

(defn- panels [panel-name]
  (case panel-name
    :app [app]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
