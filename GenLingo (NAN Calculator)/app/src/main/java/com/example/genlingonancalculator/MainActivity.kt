package com.example.genlingonancalculator

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    // I declared the TextToSpeech engine to be used for the text-to-speech feature.
    private lateinit var tts: TextToSpeech

    // I defined an array for the generation labels.
    private val generations = arrayOf(
        "Baby Boom Generation (1946–1964)",
        "Generation X (1965–1980)",
        "Millennial Generation / Gen Y (1981–1996)",
        "Generation Z / iGen (1997–2010)",
        "Generation Alpha (2010–2024)"
    )

    // This is a Translation map for slang terms across generations (index matches generation array).
    private val translationMap = mapOf(
        "cool" to arrayOf("Groovy", "Awesome", "Cool", "Lit", "Fire"),
        "awesome" to arrayOf("Far out", "Radical", "Awesome", "Dope", "Fire"),
        "attractive" to arrayOf("Foxy", "Babe", "Hot", "Snatched", "Slay"),
        "funny" to arrayOf("A riot", "Hilarious", "LOL", "Dead 💀", "Deadass funny"),
        "lie" to arrayOf("Fib", "BS", "Lie", "Cap", "Cap"),
        "truth" to arrayOf("Honest to goodness", "Straight up", "Truth", "No cap", "For real (FR)"),
        "friend" to arrayOf("Buddy", "Pal", "Bro", "Bestie", "Homie"),
        "money" to arrayOf("Bread", "Bucks", "Cash", "Stacks", "Bag"),
        "party" to arrayOf("Shindig", "Rager", "Party", "Turn up", "Get lit"),
        "angry" to arrayOf("Ticked off", "Pissed", "Mad", "Triggered", "Pressed"),
        "fashionable" to arrayOf("Hip", "Trendy", "Stylish", "Drip", "Drippy"),
        "crazy" to arrayOf("Psychedelic", "Wicked", "Insane", "Cray", "Wildin’"),
        "great" to arrayOf("Keen", "Epic", "Great", "Fire", "Goated"),
        "good" to arrayOf("Neat", "Sweet", "Nice", "Valid", "W"),
        "bad" to arrayOf("Rotten", "Lame", "Sucks", "Mid", "L"),
        "yes" to arrayOf("Right on", "Totally", "Yeah", "Bet", "Say less"),
        "no" to arrayOf("No way", "Nah", "Nope", "Cap", "That’s wild"),
        "tired" to arrayOf("Beat", "Wiped", "Exhausted", "Burnt out", "Low battery"),
        "weird" to arrayOf("Freaky", "Bizarre", "Random", "Sus", "Weird flex"),
        "jealous" to arrayOf("Green-eyed", "Envious", "Jelly", "Salty", "Mad pressed"),
        "smart" to arrayOf("Sharp", "Brainy", "Geek", "Big brain", "Sigma"),
        "silly" to arrayOf("Goofy", "Clownin’", "Silly", "Goofy ahh", "Crackhead energy"),
        "brave" to arrayOf("Gutsy", "Bold", "Fearless", "No chill", "Unbothered"),
        "romantic partner" to arrayOf("Sweetheart", "Boo", "Bae", "Simp", "Rizz target"),
        "break up" to arrayOf("Split up", "Break it off", "Dump", "Ghost", "Block and delete"),
        "happy" to arrayOf("Tickled", "Stoked", "Happy", "Vibin’", "Living rent-free"),
        "sad" to arrayOf("Blue", "Down", "Sad", "In my feels", "Soft launch sadness"),
        "embarrassed" to arrayOf("Red-faced", "Awkward", "Cringe", "Major yikes", "Cringe-core"),
        "excited" to arrayOf("Pumped", "Jazzed", "Hyped", "Sheesh", "Core memory moment"),
        "flirt" to arrayOf("Make a pass", "Hit on", "Slide in", "Shoot your shot", "Drop rizz"),
        "fight" to arrayOf("Throw hands", "Brawl", "Throw down", "Square up", "Catch these hands"),
        "annoying" to arrayOf("A pain", "Buzzkill", "Annoying", "Extra", "Cringe AF"),
        "surprised" to arrayOf("Shocked", "Whoa", "OMG", "Sheesh", "Plot twist"),
        "disappointed" to arrayOf("Bummed", "Let down", "Ugh", "SMH", "Major L"),
        "unbelievable" to arrayOf("Far out", "Unreal", "No way", "Bruh", "This ain’t real"),
        "bored" to arrayOf("Nothing to do", "Dull", "Meh", "Dead", "Bored core"),
        "ugly" to arrayOf("Plain", "Rough", "Yikes", "Ugly af", "👎"),
        "beautiful" to arrayOf("Lovely", "Fine", "Gorgeous", "Baddie", "Slay Queen"),
        "nice" to arrayOf("Pleasant", "Sweet", "Chill", "Wholesome", "Valid"),
        "mean" to arrayOf("Nasty", "Rude", "Savage", "Toxic", "Menace"),
        "gossip" to arrayOf("Talk of the town", "Juice", "Tea", "Spill", "Tea = life"),
        "truthful" to arrayOf("On the level", "For real", "No lie", "No cap", "Deadass"),
        "love" to arrayOf("Going steady", "In love", "Heart eyes", "Lowkey obsessed", "Shipping hard"),
        "confident" to arrayOf("Sure of oneself", "Cocky", "Confident", "Main character energy", "Slay king"),
        "clueless" to arrayOf("In the dark", "Clueless", "No idea", "Lost in the sauce", "???"),
        "scared" to arrayOf("Frightened", "Freaked", "Spooked", "Shook", "Jump scare"),
        "shy" to arrayOf("Bashful", "Quiet", "Introverted", "Soft boi", "Social battery low"),
        "hardworking" to arrayOf("Diligent", "Driven", "Hustling", "On the grind", "No days off"),
        "lazy" to arrayOf("Slacker", "Couch potato", "Procrastinator", "Low effort", "Sleepy vibes"),
        "food" to arrayOf("Grub", "Chow", "Snack", "Mukbang", "Munchies"),
        "delicious" to arrayOf("Yummy", "Tasty", "Delish", "Bussin", "Bussin fr fr"),
        "hungry" to arrayOf("Starved", "Ravenous", "Hungry", "Hangry", "Need a snack"),
        "gross" to arrayOf("Nasty", "Icky", "Ew", "Nasty af", "Instant ick"),
        "show off" to arrayOf("Hotshot", "Bragger", "Try-hard", "Flexer", "Clout chaser"),
        "boring" to arrayOf("Dull", "Dry", "Lame", "Mid", "Yawn"),
        "good job" to arrayOf("Well done", "Nice work", "Props", "W", "W behavior"),
        "bad idea" to arrayOf("Not smart", "Sketchy", "Suss", "Yikes", "L move"),
        "win" to arrayOf("Victory", "Score", "W", "Big W", "Huge dub"),
        "lose" to arrayOf("Defeat", "Fail", "Loss", "L", "Take the L"),
        "relax" to arrayOf("Take five", "Chill", "Netflix & chill", "Vibe", "Recharge")
    )

    // This is to store the selected translation result.
    private var translationResult: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This is where I initialized the UI components.
        val inputText = findViewById<AutoCompleteTextView>(R.id.inputText)
        val resultText = findViewById<TextView>(R.id.resultText)
        val speakImage = findViewById<ImageView>(R.id.speakImage)
        val infoButton = findViewById<ImageView>(R.id.infoButton)
        val translateButton = findViewById<ImageView>(R.id.translateButton)

        val boomerButton = findViewById<ImageButton>(R.id.boomerButton)
        val genxButton = findViewById<ImageButton>(R.id.genxButton)
        val millennialButton = findViewById<ImageButton>(R.id.millennialButton)
        val genzButton = findViewById<ImageButton>(R.id.genzButton)
        val genalphaButton = findViewById<ImageButton>(R.id.genalphaButton)

        // This is to initialize Text-to-Speech engine.
        tts = TextToSpeech(this, this)

        // I set up autocomplete suggestions in input field.
        // I created a list to hold both keywords and related words.
        val allSuggestions = mutableListOf<String>()

        // This adds the translation map keys which are the slang terms.
        allSuggestions.addAll(translationMap.keys)

        // This adds the related words from the translation map which are the slang variations.
        translationMap.values.forEach { translations ->
            allSuggestions.addAll(translations)
        }

        // This removes any duplicates if there is any.
        val uniqueSuggestions = allSuggestions.distinct()

        // I set the AutoCompleteTextView with the updated list of suggestions.
        inputText.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, uniqueSuggestions))


        // I also used Utility function to apply border to the selected generation button for emphasis.
        fun applyBorderToButton(button: ImageButton) {
            button.setBackgroundResource(R.drawable.button_border)
        }

        // This clears the border from all buttons.
        fun clearBorders() {
            boomerButton.setBackgroundResource(0)
            genxButton.setBackgroundResource(0)
            millennialButton.setBackgroundResource(0)
            genzButton.setBackgroundResource(0)
            genalphaButton.setBackgroundResource(0)
        }

        // This is the button click logic for each generation.
        // It works by retrieving input, processes translation, updates the result.
        boomerButton.setOnClickListener {
            clearBorders()
            applyBorderToButton(boomerButton)
            val input = inputText.text.toString().trim().lowercase(Locale.getDefault())
            translationResult = getTranslation(input, 0)  // This is to store the result temporarily.
        }

        genxButton.setOnClickListener {
            clearBorders()
            applyBorderToButton(genxButton)
            val input = inputText.text.toString().trim().lowercase(Locale.getDefault())
            translationResult = getTranslation(input, 1)  // This is to store the result temporarily.
        }

        millennialButton.setOnClickListener {
            clearBorders()
            applyBorderToButton(millennialButton)
            val input = inputText.text.toString().trim().lowercase(Locale.getDefault())
            translationResult = getTranslation(input, 2)  // This is to store the result temporarily.
        }

        genzButton.setOnClickListener {
            clearBorders()
            applyBorderToButton(genzButton)
            val input = inputText.text.toString().trim().lowercase(Locale.getDefault())
            translationResult = getTranslation(input, 3)  // This is to store the result temporarily.
        }

        genalphaButton.setOnClickListener {
            clearBorders()
            applyBorderToButton(genalphaButton)
            val input = inputText.text.toString().trim().lowercase(Locale.getDefault())
            translationResult = getTranslation(input, 4)  // This is to store the result temporarily.
        }

        // When the translate button is clicked, the result is shown.
        translateButton.setOnClickListener {
            if (!translationResult.isNullOrEmpty()) {
                resultText.text = translationResult  // This make sures that the result only show after clicking the translate button.
            } else {
                resultText.text = "Please choose a generation first."
            }
        }


        // This read the result or translation out loud when the speak icon button is clicked.
        speakImage.setOnClickListener {
            val textToSpeak = resultText.text.toString()
            if (textToSpeak.isNotBlank()) {
                tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        // When the info button is clicked, the InstructionActivity screen is opened.
        infoButton.setOnClickListener {
            val intent = Intent(this, InstructionActivity::class.java)
            startActivity(intent)
        }
    }

    // This gets the translated word based on generation index.
    private fun getTranslation(word: String, generationIndex: Int): String {
        val key = word.lowercase(Locale.getDefault())
        val meanings = translationMap[key]

        if (meanings != null && generationIndex in meanings.indices) {
            return "\"$word\" for ${generations[generationIndex]}:\n${meanings[generationIndex]}"
        }

        for ((original, translations) in translationMap) {
            for (i in translations.indices) {
                if (translations[i].equals(word, ignoreCase = true)) {
                    return "\"$word\" is how ${generations[generationIndex]} say \"$original\": ${translations[generationIndex]}"
                }
            }
        }

        return "Sorry, we don’t have a generational translation for \"$word\" yet."
    }

    // THis initializes the text-to-speech settings.
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
        } else {
            Toast.makeText(this, "TTS initialization failed", Toast.LENGTH_SHORT).show()
        }
    }

    // This stops and shutdown the text-to-speech when the activity is destroyed.
    override fun onDestroy() {
        // Shutdown TTS to free resources
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}