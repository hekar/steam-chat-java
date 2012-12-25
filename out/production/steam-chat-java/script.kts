// Module script for production
import kotlin.modules.*
fun project() {
    module("steam-chat-java") {
        sources += "C:/Users/hekar/Documents/GitHub/steam-chat-java/src/org/steamchat/model/chat/ChatStatus.kt"
        sources += "C:/Users/hekar/Documents/GitHub/steam-chat-java/src/org/steamchat/model/chat/InstantMessage.kt"
        sources += "C:/Users/hekar/Documents/GitHub/steam-chat-java/src/org/steamchat/model/connection/Method.kt"
        sources += "C:/Users/hekar/Documents/GitHub/steam-chat-java/src/org/steamchat/model/user/Account.kt"
        sources += "C:/Users/hekar/Documents/GitHub/steam-chat-java/src/org/steamchat/model/user/Session.kt"
        sources += "C:/Users/hekar/Documents/GitHub/steam-chat-java/src/org/steamchat/model/user/Status.kt"
        sources += "C:/Users/hekar/Documents/GitHub/steam-chat-java/src/org/steamchat/remote/service/SteamChatService.kt"
        sources += "C:/Users/hekar/Documents/GitHub/steam-chat-java/src/org/steamchat/remote/service/SteamHttpClient.kt"
        // Classpath
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/charsets.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/deploy.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/javaws.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/jce.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/jfr.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/jfxrt.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/jsse.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/management-agent.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/plugin.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/resources.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/rt.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/ext/access-bridge-64.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/ext/dnsns.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/ext/jaccess.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/ext/localedata.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/ext/sunec.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/ext/sunjce_provider.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/ext/sunmscapi.jar"
        classpath += "C:/Program Files/Java/jdk1.7.0_07/jre/lib/ext/zipfs.jar"
        // Output directory, commented out
        //         classpath += "C:/Users/hekar/Documents/GitHub/steam-chat-java/out/production/steam-chat-java"
        classpath += "C:/Users/hekar/Documents/GitHub/steam-chat-java/lib/kotlin-runtime.jar"
        classpath += "C:/Users/hekar/Documents/GitHub/steam-chat-java/lib/commons-codec-1.6.jar"
        classpath += "C:/Users/hekar/Documents/GitHub/steam-chat-java/lib/commons-logging-1.1.1.jar"
        classpath += "C:/Users/hekar/Documents/GitHub/steam-chat-java/lib/fluent-hc-4.2.2.jar"
        classpath += "C:/Users/hekar/Documents/GitHub/steam-chat-java/lib/httpclient-4.2.2.jar"
        classpath += "C:/Users/hekar/Documents/GitHub/steam-chat-java/lib/httpclient-cache-4.2.2.jar"
        classpath += "C:/Users/hekar/Documents/GitHub/steam-chat-java/lib/httpcore-4.2.2.jar"
        classpath += "C:/Users/hekar/Documents/GitHub/steam-chat-java/lib/httpmime-4.2.2.jar"
        // Java Source Roots
        classpath += "C:/Users/hekar/Documents/GitHub/steam-chat-java/src"
        // External annotations
        annotationsPath += "C:/Program Files (x86)/JetBrains/IntelliJ IDEA Community Edition 12.0/lib/jdkAnnotations.jar"
        annotationsPath += "C:/Users/hekar/.IdeaIC12/config/plugins/Kotlin/kotlinc/lib/kotlin-jdk-annotations.jar"
    }
}
