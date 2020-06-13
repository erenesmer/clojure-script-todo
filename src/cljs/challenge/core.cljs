(ns challenge.core
  (:require
   [reagent.core :as reagent]
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [challenge.routes :as routes]
   [challenge.config :as config]
   [challenge.app.views :as views]
   [challenge.app.events :as events]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/app] root-el)))

(defn init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
