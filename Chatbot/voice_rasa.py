import requests
import speech_recognition as sr
import pyttsx3

bot_message = ""
message = ""

while bot_message != "Bye":

    #1. Speech to text
    r = sr.Recognizer()  # Initialize recognizer
    with sr.Microphone() as source:  # use microphone as source
        print("Speak Anything: ")
        audio = r.listen(source)  # listen to source
        try:
            message = r.recognize_google(audio)  # use recognizer to convert audio into text
            print("You said : {}".format(message))
        except:
            print("Sorry, could not recognize your voice")

    if len(message) == 0:
        continue
    print("Sending message now...")

    #2. Externally sending the text to the Rasa chatbot
    r = requests.post("http://localhost:5002/webhooks/rest/webhook", json={"message": message})

    print("Bot says, ", end=" ")
    for i in r.json():
        bot_message = i["text"]
        print(f"{i['text']}")

    ##3. Text to Speech
    engine = pyttsx3.init()
    rate = engine.getProperty("rate")
    engine.setProperty("rate", 125)

    voices = engine.getProperty("voices")
    engine.setProperty("voice", voices[1].id)

    engine.say(bot_message)
    engine.runAndWait()