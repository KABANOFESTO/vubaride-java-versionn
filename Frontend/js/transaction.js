document.addEventListener("DOMContentLoaded", () => {
    // Load translations JSON
    fetch("translations.json")
        .then(response => response.json())
        .then(translations => {
            // Set the default language to English
            const languageSelect = document.getElementById("language-select");
            setLanguage("en", translations);

            // Add event listener to change language
            languageSelect.addEventListener("change", () => {
                setLanguage(languageSelect.value, translations);
            });
        });

    function setLanguage(language, translations) {
        document.getElementById("title").textContent = translations[language].title;
        document.getElementById("home").textContent = translations[language].home;
        document.getElementById("about").textContent = translations[language].about;
        document.getElementById("services").textContent = translations[language].services;
        document.getElementById("gallery").textContent = translations[language].gallery;
        document.getElementById("contact").textContent = translations[language].contact;
        document.getElementById("book_now").textContent = translations[language].book_now;
        document.getElementById("about_us").textContent = translations[language].about_us;
        document.getElementById("welcome").textContent = translations[language].welcome;
        document.getElementById("description").textContent = translations[language].description;
    }
});