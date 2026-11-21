# Maninder Bhu Naksha — Android Studio project

## इस संस्करण में
- Android ऐप का नाम और होम स्क्रीन
- आधिकारिक HP Bhu-Naksha पोर्टल WebView में
- HimBhoomi भूमि रिकॉर्ड पोर्टल WebView में
- eHimBhoomi नागरिक पोर्टल में डिजिटाइजेशन स्थिति का लिंक
- जिला/तहसील/गाँव/खसरा के लिए फिलहाल केवल UI इनपुट

## अभी क्या नहीं है
- ऐप के अपने search बॉक्स से सरकारी रिकॉर्ड खोजना
- सरकारी GIS सर्वर/API से सीधा कनेक्शन
- ऑफलाइन कैडस्ट्रल मैप डेटा
- APK build/signing

खसरा सर्च और वास्तविक GIS लेयर जोड़ने के लिए HP Directorate of Land Records से अधिकृत API/डेटाबेस विवरण और उपयोग की अनुमति चाहिए। सरकारी Bhu-Naksha गाइड में हिमाचल के custom GIS services के लिए Settlement तथा Consolidation डेटाबेस के host, port, database और credentials की जरूरत बताई गई है। निजी credentials ऐप में hard-code न करें; backend पर सुरक्षित रखें।

## Android Studio में खोलना
1. ZIP extract करें।
2. Android Studio में `ManinderBhuNaksha` फ़ोल्डर खोलें।
3. Gradle sync पूरा होने दें।
4. Emulator या Android फोन पर Run दबाएँ।
5. APK के लिए Build > Build Bundle(s) / APK(s) > Build APK(s) चुनें।

## आधिकारिक स्रोत
- https://himachal.nic.in/revenue
- https://bhunakshahp.nic.in/
- https://himbhoomilmk.nic.in/viewLandRecords.aspx
- https://trainingehimbhoomi.hp.gov.in/citizen
