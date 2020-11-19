## ask name and greet further
* greet
  - utter_greet_ask_name
* inform_name
  - utter_greet_name
* mood_happy
    - utter_happy

## sad path 1
* greet
  - utter_greet_ask_name
* inform_name
  - utter_greet_name
* mood_unhappy
  - utter_cheer_up
* affirm
  - utter_happy

## sad path 2
* greet
  - utter_greet_ask_name
* inform_name
  - utter_greet_name
* mood_unhappy
  - utter_cheer_up
* deny
  - utter_sorry

## say goodbye
* goodbye
  - utter_goodbye

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Philip"}
* inform_name{"first_name":"Philip"}
    - utter_greet_name
* mood_happy
    - utter_happy

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Philip"}
* inform_name{"first_name":"Philip"}
    - utter_greet_name
* mood_happy
    - utter_happy

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Philip"}
* inform_name{"first_name":"Philip"}
    - utter_greet_name
* mood_unhappy
    - utter_cheer_up
* affirm
    - utter_happy

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Philip"}
* inform_name{"first_name":"Philip"}
    - utter_greet_name
* mood_happy
    - utter_happy

## Covid-19 Scenario 1 Happy-no
* mood_happy
    - utter_happy
* deny
    - utter_goodbye

## Covid-19 Scenario 2 Happy-yes-corona-yes
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* affirm
    - utter_corona_tell_more
* corona
    - utter_corona_yes_correct

## Covid-19 Scenario 3 Happy-yes-corona-no
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* deny
    - utter_corona_no

## Covid-19 Scenario 4 Happy-yes-corona-yes-lonely
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* affirm
    - utter_corona_tell_more
* corona
    - utter_corona_yes_correct
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends

## Covid-19 Scenario 5 Happy-yes-corona-no-lonely
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* deny
    - utter_corona_no
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends

## Covid-19 Scenario 4 Happy-yes-corona-yes-lonely-sickyes
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* affirm
    - utter_corona_tell_more
* corona
    - utter_corona_yes_correct
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* affirm
    - utter_corona_sick_talk

## Covid-19 Scenario 4 Happy-yes-corona-yes-lonely-sickyes-talkyes
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* affirm
    - utter_corona_tell_more
* corona
    - utter_corona_yes_correct
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* affirm
    - utter_corona_sick_talk
* affirm
    - utter_corona_stay_safe

## Covid-19 Scenario 4 Happy-yes-corona-yes-lonely-sickyes-talkno
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* affirm
    - utter_corona_tell_more
* corona
    - utter_corona_yes_correct
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* affirm
    - utter_corona_sick_talk
* deny
    - utter_corona_stay_safe

## Covid-19 Scenario 5 Happy-yes-corona-no-lonely-sickno
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* deny
    - utter_corona_no
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* deny
    - utter_corona_sick_no

## Covid-19 Scenario 5 Happy-yes-corona-no-lonely-sickno-yes
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* deny
    - utter_corona_no
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* deny
    - utter_corona_sick_no
* affirm
    - utter_corona_stay_safe

## Covid-19 Scenario 5 Happy-yes-corona-no-lonely-sickno-no
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* deny
    - utter_corona_no
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* deny
    - utter_corona_sick_no

## New Story

* greet
    - utter_greet_ask_name
    - utter_greet_name
    - slot{"first_name":"Thea"}
* inform_name{"first_name":"Thea"}
* mood_happy
    - utter_happy
    - utter_ask_corona
* affirm

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Lisa"}
* inform_name{"first_name":"Lisa"}
    - utter_greet_name
* mood_happy
    - utter_happy
    - utter_ask_corona
* affirm

## New Story

    - utter_greet_ask_name
* greet
    - slot{"first_name":"Philip"}
* inform_name{"first_name":"Philip"}
    - slot{"first_name":"Philip"}
    - utter_greet_name
* mood_happy
    - utter_happy
    - utter_ask_corona
* affirm
    - utter_corona_no
* deny
* corona_lonely
    - utter_corona_lonely

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Thea"}
* inform_name{"first_name":"Thea"}
    - slot{"first_name":"Thea"}
    - utter_greet_name
* mood_unhappy
    - utter_cheer_up
* deny
    - utter_sorry

## New Story

    - utter_greet_ask_name
    - slot{"first_name":"Emma"}
* greet
    - utter_greet_name
    - utter_happy
* mood_happy
* affirm
    - utter_ask_corona
* deny

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Fredrik"}
* inform_name{"first_name":"Fredrik"}
    - utter_greet_name
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* deny
    - utter_corona_no
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* deny
    - utter_corona_sick_no
* affirm
    - utter_corona_stay_safe
* goodbye
    - utter_goodbye

## New Story

* greet
    - utter_greet_ask_name

## New Story

    - utter_greet_ask_name
    - slot{"first_name":"Thea"}
* greet
    - utter_ask_corona
* deny
    - utter_corona_no
    - utter_corona_lonely
* corona_lonely
* corona_friends
    - utter_corona_friends
    - utter_corona_sick_no
* deny
* deny
    - utter_corona_stay_safe
* goodbye
    - utter_goodbye

## New Story

    - utter_greet_ask_name
* greet
    - slot{"first_name":"Anita"}
* inform_name{"first_name":"Anita"}

## New Story

    - utter_greet_ask_name
* greet
    - utter_greet_name
    - slot{"first_name":"Tien"}
* inform_name{"first_name":"Tien"}
    - slot{"first_name":"Tien"}
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* deny
    - utter_corona_no
* corona_lonely

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Jonathan"}
* inform_name{"first_name":"Jonathan"}
    - slot{"first_name":"Jonathan"}
    - utter_greet_name
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* deny
    - utter_corona_no
    - utter_corona_lonely
* corona_lonely
    - utter_corona_friends
* corona_friends
* deny
    - utter_corona_sick_no
* affirm
    - utter_corona_stay_safe
    - utter_goodbye
* goodbye

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Lars"}
* inform_name{"first_name":"Lars"}
    - slot{"first_name":"Lars"}
    - utter_greet_name
    - utter_happy
* mood_happy
    - utter_ask_corona
* affirm
    - utter_corona_tell_more
* corona

## New Story

* greet
    - utter_greet_ask_name
* inform_name{"first_name":"Tien"}
    - utter_greet_name
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* affirm
    - utter_corona_tell_more
* corona
    - utter_corona_yes_correct
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* affirm
    - utter_corona_sick_talk
* denys
    - utter_corona_stay_safe
* goodbye
    - utter_goodbye

## New Story

    - utter_greet_ask_name
* greet
    - slot{"first_name":"Sunniva"}
* inform_name{"first_name":"Sunniva"}
    - utter_greet_name
* mood_unhappy
    - utter_cheer_up
* deny

## New Story

    - utter_greet_ask_name
* greet
    - slot{"first_name":"Liva"}
* inform_name{"first_name":"Liva"}
    - slot{"first_name":"Liva"}
    - utter_greet_name
* mood_unhappy
    - utter_cheer_up
* deny
    - utter_sorry
* goodbye
    - utter_goodbye

## New Story

* greet
    - utter_greet_ask_name
    - utter_greet_name
    - slot{"first_name":"Philip"}
* inform_name{"first_name":"Philip"}
    - slot{"first_name":"Philip"}
* mood_happy
    - utter_happy
* deny
    - utter_goodbye

## New Story

* greet
    - utter_greet_ask_name
    - utter_greet_name
    - slot{"first_name":"Lars"}
* inform_name{"first_name":"Lars"}
    - slot{"first_name":"Lars"}
* mood_happy
    - utter_happy
    - utter_ask_corona
* affirm
    - utter_corona_tell_more
    - utter_corona_yes_correct
* corona
* corona_lonely
    - utter_corona_lonely
    - utter_corona_friends
* corona_friends
    - utter_corona_sick_no
* deny
    - utter_corona_stay_safe
* deny
    - utter_goodbye
* goodbye

## New Story

    - utter_greet_ask_name
* greet
    - slot{"first_name":"TIen"}
* inform_name{"first_name":"TIen"}
    - slot{"first_name":"TIen"}
    - utter_greet_name
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* affirm
    - utter_corona_tell_more
* corona
    - utter_corona_yes_correct
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* affirm
    - utter_corona_sick_talk
* deny
    - utter_corona_stay_safe

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Tien"}
* inform_name{"first_name":"Tien"}
    - slot{"first_name":"Tien"}
    - utter_greet_name
* affirm
    - utter_ask_corona
* deny
    - utter_corona_no
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* deny
    - utter_corona_sick_no
* affirm
    - utter_corona_stay_safe

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Lars"}
* inform_name{"first_name":"Lars"}
    - utter_greet_name
* mood_happy
    - utter_happy

## New Story

* greet
    - utter_greet_ask_name
    - slot{"first_name":"Lars"}
* inform_name{"first_name":"Lars"}
    - slot{"first_name":"Lars"}
    - utter_greet_name
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* deny
    - utter_corona_no
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* deny
    - utter_corona_sick_no
* affirm
    - utter_corona_stay_safe
* goodbye
    - utter_goodbye

## interactive_story_1
* greet
    - utter_greet_ask_name
* affirm

## interactive_story_2
* greet
    - utter_greet_ask_name
* inform_name{"first_name": "Lars"}
    - slot{"first_name": "Lars"}
    - utter_greet_name
* mood_happy
    - utter_happy
* affirm
    - utter_ask_corona
* deny
    - utter_corona_no
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* deny
    - utter_corona_sick_no
* affirm
    - utter_corona_stay_safe

## interactive_story_1
* greet
    - utter_greet_ask_name
* inform_name{"first_name": "Philip"}
    - slot{"first_name": "Philip"}
    - utter_greet_name
* mood_unhappy
    - utter_cheer_up
* affirm
    - utter_happy
* affirm
    - utter_ask_corona
* affirm
    - utter_corona_tell_more
* corona
    - utter_corona_yes_correct
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* deny
    - utter_corona_sick_no
* deny
    - utter_corona_stay_safe

## interactive_story_1
* greet
    - utter_greet_ask_name
* inform_name{"first_name": "Fredrik"}
    - slot{"first_name": "Fredrik"}
    - utter_greet_name
* mood_unhappy
    - utter_cheer_up
* affirm
    - utter_happy
* affirm
    - utter_ask_corona
* affirm
    - utter_corona_tell_more
* corona
    - utter_corona_yes_correct
* corona_lonely
    - utter_corona_lonely
* corona_friends
    - utter_corona_friends
* affirm
    - utter_corona_sick_talk
* affirm
    - utter_corona_stay_safe
* goodbye
    - utter_goodbye
