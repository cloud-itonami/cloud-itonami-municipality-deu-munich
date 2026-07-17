(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Munich (Landeshauptstadt
  München) -- a Wave 1b addition per ADR-2607171400 addendum 2, joining
  the cloud-itonami-municipality-* compliance-fact family of
  ADR-2607141700 (cloud-itonami-compliance-fact-federation; see
  cloud-itonami-municipality-deu-berlin for the German sibling).

  Every entry cites an OFFICIAL stadt.muenchen.de Stadtrecht URL --
  never fabricated. An ordinance not in this table has NO spec-basis,
  full stop; extend `catalog`, do not invent an id/url/number.

  Both entries below were verified on 2026-07-17 by reading the live
  Stadtrecht page for each Satzung on stadt.muenchen.de (the city's
  official municipal-law database, stadt.muenchen.de/rathaus/stadtrecht):
  each title, 'vom' date, MüABl. publication reference and amendment
  history stated here was read back from the cited page, not guessed.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"munich"
   [{:ordinance/id "munich.gruenanlagensatzung-2012"
     :ordinance/title "Satzung über die Benutzung der städtischen öffentlichen Grünanlagen (Grünanlagensatzung)"
     :ordinance/municipality "munich"
     :ordinance/country "DEU"
     :ordinance/kind :ordinance
     :ordinance/number "Stadtrecht Nr. 810; vom 15. Juni 2012 (MüABl. S. 197, bekanntgemacht 10.07.2012)"
     :ordinance/url "https://stadt.muenchen.de/rathaus/stadtrecht/vorschrift/810/version1/0.html"
     :ordinance/url-provenance :official-stadt-muenchen-de
     :ordinance/enacted-date "2012-06-15"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:public-space :parks}}
    {:ordinance/id "munich.strassenreinigungssatzung-1979"
     :ordinance/title "Satzung über die Straßenreinigung der Landeshauptstadt München (Straßenreinigungssatzung)"
     :ordinance/municipality "munich"
     :ordinance/country "DEU"
     :ordinance/kind :ordinance
     :ordinance/number "Stadtrecht Nr. 240; vom 4. Dezember 1979 (MüABl. S. 278, bekanntgemacht 20.12.1979)"
     :ordinance/url "https://stadt.muenchen.de/rathaus/stadtrecht/vorschrift/240/version3/0.html"
     :ordinance/url-provenance :official-stadt-muenchen-de
     :ordinance/enacted-date "1979-12-04"
     :ordinance/last-revised-date "2019-04-24"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:public-space :sanitation}}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-deu-munich Wave 1b (ADR-2607171400 "
                 "addendum 2 / family ADR-2607141700): "
                 (count (get catalog "munich")) " Munich entries seeded with "
                 "official stadt.muenchen.de Stadtrecht citations. Extend "
                 "`ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
