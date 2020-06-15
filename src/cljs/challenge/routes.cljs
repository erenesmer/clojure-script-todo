(ns challenge.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import [goog History]
           [goog.history EventType])
  (:require
    [secretary.core :as secretary]
    [goog.events :as gevents]
    [re-frame.core :as rf]
    [challenge.app.events :as events]
    [challenge.app.views :refer [app]]
    ))

(defn hook-browser-navigation! []
  (doto (History.)
    (gevents/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/" []
            (rf/dispatch [::events/set-active-panel [app :app]])
            )

  (hook-browser-navigation!))
