(ns challenge.db
  (:require [cljs.spec.alpha :as s]))

;; -- Specs
(s/def ::int int?)
(s/def ::title string?)
(s/def ::done boolean?)
(s/def ::created string?)
(s/def ::todo (s/keys :req-un [::id ::title ::done ::created]))
(s/def ::todos
  (s/and (s/map-of ::id ::todo)
         #(instance? PersistentTreeMap %)))

(s/def ::showing
  #{:all
    :active
    :done})

(s/def ::name string?)

(s/def ::db (s/keys :req-un [::todos ::showing ::name]))

;; -- DB
(def default-db
  {:name    "we love challengeZ"
   :todos   (sorted-map)
   :showing :all})
