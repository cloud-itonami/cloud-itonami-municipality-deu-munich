(ns culture.facts
  "Regional-culture catalog for Munich (Landeshauptstadt München) -- local
  dishes, beverages, festivals and heritage sites, piggybacked onto this
  municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"munich"
   [{:culture/id "munich.dish.weisswurst"
     :culture/name "Weisswurst"
     :culture/name-local "Weißwurst"
     :culture/municipality "munich"
     :culture/country "DEU"
     :culture/kind :dish
     :culture/summary "Traditional Bavarian sausage of minced veal and pork fatback, flavored with parsley and spices, traditionally eaten as a mid-morning snack."
     :culture/url "https://en.wikipedia.org/wiki/Weisswurst"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "munich.dish.obatzda"
     :culture/name "Obatzda"
     :culture/municipality "munich"
     :culture/country "DEU"
     :culture/kind :dish
     :culture/summary "Bavarian cheese spread of aged soft cheese and butter seasoned with paprika, typically served with bread or pretzels in beer gardens."
     :culture/url "https://en.wikipedia.org/wiki/Obatzda"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "munich.dish.leberkaese"
     :culture/name "Leberkäse"
     :culture/municipality "munich"
     :culture/country "DEU"
     :culture/kind :dish
     :culture/summary "Southern German specialty of finely ground beef, pork and bacon baked as a loaf with a crunchy brown crust, typically served hot on a roll with mustard."
     :culture/url "https://en.wikipedia.org/wiki/Leberk%C3%A4se"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "munich.dish.schweinshaxe"
     :culture/name "Schweinshaxe"
     :culture/municipality "munich"
     :culture/country "DEU"
     :culture/kind :dish
     :culture/summary "Roasted ham hock (pork knuckle), especially popular in Bavaria, slow-roasted and served with potato and cabbage sides."
     :culture/url "https://en.wikipedia.org/wiki/Schweinshaxe"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "munich.beverage.helles"
     :culture/name "Helles"
     :culture/municipality "munich"
     :culture/country "DEU"
     :culture/kind :beverage
     :culture/summary "Traditional German pale lager beer, mainly produced in southern Germany and particularly Munich."
     :culture/url "https://en.wikipedia.org/wiki/Helles"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "munich.festival.oktoberfest"
     :culture/name "Oktoberfest"
     :culture/municipality "munich"
     :culture/country "DEU"
     :culture/kind :festival
     :culture/summary "The world's largest Volksfest, held annually on Munich's Theresienwiese from mid-September to early October; first held in 1810."
     :culture/url "https://en.wikipedia.org/wiki/Oktoberfest"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "munich.festival.auer-dult"
     :culture/name "Auer Dult"
     :culture/municipality "munich"
     :culture/country "DEU"
     :culture/kind :festival
     :culture/summary "Traditional fair combining a market and folk festival, held three times a year in Munich's Au district."
     :culture/url "https://en.wikipedia.org/wiki/Auer_Dult"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "munich.heritage.frauenkirche"
     :culture/name "Frauenkirche"
     :culture/name-local "Dom zu Unserer Lieben Frau"
     :culture/municipality "munich"
     :culture/country "DEU"
     :culture/kind :heritage
     :culture/summary "Late Gothic brick cathedral built 1468-1488 with twin domed towers; a landmark and symbol of the Bavarian capital."
     :culture/url "https://en.wikipedia.org/wiki/Frauenkirche,_Munich"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "munich.heritage.englischer-garten"
     :culture/name "English Garden"
     :culture/name-local "Englischer Garten"
     :culture/municipality "munich"
     :culture/country "DEU"
     :culture/kind :heritage
     :culture/summary "Large public park in the center of Munich created in 1789; at 3.7 km² among the world's largest urban parks."
     :culture/url "https://en.wikipedia.org/wiki/English_Garden_(Munich)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-municipality-deu-munich culture catalog "
                 "(ADR-2607171400): " (count (get catalog "munich"))
                 " Munich entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
