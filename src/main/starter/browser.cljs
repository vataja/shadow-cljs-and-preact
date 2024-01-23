(ns starter.browser
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

(defn component-main []
  (let [state (r/atom {})]
    (fn []
      [:div
       [:input
        {:type "text"
         :on-change (fn [e]
                      (reset! state (-> e .-target .-value)))}]
       [:div "Reversed: "
        (reverse @state)]])))

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start []
  (js/console.log "start")
  (rdom/render [component-main]
               (js/document.getElementById "app")))

(defn init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (js/console.log "init")
  (start))

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))
