# Use a base image with Linux and necessary tools
FROM ubuntu:latest

# Install required packages (curl and tar)
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Install necessary packages (including OpenJDK 11)
RUN apt-get update && apt-get install -y \
    openjdk-11-jdk \
    && rm -rf /var/lib/apt/lists/*

# Set Java environment variables (optional)
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME/bin


# Set the working directory
WORKDIR /app

# Download the SourceMeter archive
RUN curl -LO https://www.sourcemeter.com/static/SourceMeter-10.2.0-x64-Linux.tgz

# Unarchive the downloaded file
RUN tar -xzf SourceMeter-10.2.0-x64-Linux.tgz

# Cleanup - remove the downloaded archive
RUN rm SourceMeter-10.2.0-x64-Linux.tgz

# Run the SourceMeter Java analyzer
ENTRYPOINT [ \
"/app/SourceMeter-10.2.0-x64-Linux/Java/AnalyzerJava", \
"-projectName=source-meter", \
"-projectBaseDir=/workdir", \
"-resultsDir=/workdir/source-meter", \
"-runPMD=false", \
"-runLIM2Patterns=false", \
"-runFB=false", \
"-runUDM=false", \
"-runDCF=false", \
"-runMetricHunter=false", \
"-runAndroidHunter=false", \
"-runVulnerabilityHunter=false", \
"-runFaultHunter=false", \
"-runRTEHunter=false" \
]
