# Use a base image with Linux and necessary tools
FROM ubuntu:latest

# Install required packages (curl and tar)
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Install required packages (curl and tar)
RUN apt-get update && apt-get install -y \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Install necessary packages (including OpenJDK 17)
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    && rm -rf /var/lib/apt/lists/*

# Set Java environment variables (optional)
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME/bin


# Set the working directory
WORKDIR /app

# Download the SourceMeter archive
RUN curl -LO https://github.com/clarkware/jdepend/releases/download/2.10/jdepend-2.10.zip

# Unarchive the downloaded file
RUN unzip jdepend-2.10.zip

# Cleanup - remove the downloaded archive
RUN rm jdepend-2.10.zip

# Run the Jdepend Java analyzer
ENTRYPOINT [ \
"java", \
"-cp", \
"/app/jdepend-2.10/lib/jdepend-2.10.jar", \
"jdepend.xmlui.JDepend", \
"-file", \
"/workdir/jdepend.report.xml", \
"/workdir" \
]
