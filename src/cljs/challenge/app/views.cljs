(ns challenge.app.views
  (:require
    [re-frame.core :as re-frame]
    [challenge.app.subs :as subs]))


;; main


;; todo-checkbox
(defn todo-checkbox [done]
  [:div.todo-checkbox
   {:class (str (if done "done"))}])

;; todo-item
(defn todo-item [todo]
  [:div
   {:class "todo-item"}
   [:div
    {:class "todo-data"}
    [todo-checkbox (get todo :done)]
    [:p.todo-title
     {:class (str (if (get todo :done) "done"))}
     (get todo :title)]]
   [:div
    {:class "todo-toolbox"}
    [:a {:href "#" :class "toggle-todo-button"} "⌘"]
    [:a {:href "#" :class "delete-todo-button"} "⌦"]]
   [:div {:class (str "todo-color " (get todo :color))}]])

;; todo-container
(defn todo-container [todos]
  [todo-item
   {:id 1 :title "this is an example todo item" :done true :color "blue"}])

;; app
(defn app []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div {:class "todo-container"}
     [todo-container]]))

;(defn- panels [panel-name]
;  (case panel-name
;        :app [app]
;        [:div]))
;
;(defn show-panel [panel-name]
;  [panels panel-name])
;
;(defn main-panel []
;  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
;    [show-panel @active-panel]))
