package com.cubisoft.contributor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationContextContributor implements InfoContributor {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private Environment environment;

    @Override
    public void contribute(Info.Builder builder) {

        Map<String, Object> contextMap = new HashMap<>();

        // Application Startup Time
        Instant startupTime = Instant.ofEpochMilli(context.getStartupDate());
        contextMap.put("application-startup-time", startupTime.toString());

        // Application Uptime
        Duration uptime = Duration.ofMillis(System.currentTimeMillis() - context.getStartupDate());
        contextMap.put("application-uptime", formatDuration(uptime));

        // Application Name
        contextMap.put("application-name", context.getApplicationName());

        // Active Profiles
        contextMap.put("active-profiles", context.getEnvironment().getActiveProfiles());

        // JVM Details
        contextMap.put("jvm-name", ManagementFactory.getRuntimeMXBean().getVmName());
        contextMap.put("jvm-version", ManagementFactory.getRuntimeMXBean().getVmVersion());
        contextMap.put("jvm-start-time", Instant.ofEpochMilli(ManagementFactory.getRuntimeMXBean().getStartTime()).toString());

        // OS Details
        contextMap.put("os-name", System.getProperty("os.name"));
        contextMap.put("os-arch", System.getProperty("os.arch"));
        contextMap.put("os-version", System.getProperty("os.version"));

        // Git Details (requires git-commit-id-plugin)
        Map<String, Object> gitMap = new HashMap<>();
        gitMap.put("branch", environment.getProperty("git.branch"));
        gitMap.put("commit-id", environment.getProperty("git.commit.id.abbrev"));
        gitMap.put("commit-time", environment.getProperty("git.commit.time"));
        builder.withDetail("git", gitMap);

        // Add context details
        builder.withDetail("application-context", contextMap);
    }

    // Utility method to format uptime nicely
    private String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
    }
}
