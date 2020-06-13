(ns challenge.app.views
  (:require
    [re-frame.core :as re-frame]
    [challenge.app.subs :as subs]
    [challenge.icons :refer [archieve-icon delete-icon done-icon trash-icon]]))

;; todo-item
(defn todo-item [todo]
  [:div.todo-item
   [:div.todo-data
    [:h2.todo-title
     {:class (str (if (get todo :done) "done"))}
     (get todo :title)]
    [:p.created-at (str "Created: " (get todo :created-at))]]
   [:div.todo-toolbox
    [:a.toggle-todo-button {:href "#"}
     [done-icon]]
    [:a.delete-todo-button {:href "#"}
     [trash-icon]]]
   [:div.todo-status-color {:class (if (get todo :done) "done")}]])

;; add-todo
(defn create-todo []
  [:div.create-todo
   [:input.create-todo-input {:type "text" :placeholder "Create To Do"}]
   [:a.create-todo-button {:href "#"} [done-icon]]])

;; todo-container
(defn todo-container [todos]
  [:<>
   [create-todo]
   (for [todo todos]
     ^{:key (str (get todo :id))}
     [todo-item
      todo])])

;; app
(defn
  app []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div.todo-container
     [todo-container
      [{:id         1
        :title      "this is an example todo item lorem sdjhfakdsjfhajksdhfjasd fasdfjkahsdfkjahdskf asdhjfajksdfhak dskfjhads hjkfa dksjfhaksjd fhakjds fhakjs dkfjhas dfha skjdfhj kasdajks dfhads"
        :done       true
        :created-at (str (js/Date.))
        :color      "blue"}
       {:id         2
        :title      "this is an second example todo item"
        :done       false
        :created-at (str (js/Date.))
        :color      "red"}]]]))

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
