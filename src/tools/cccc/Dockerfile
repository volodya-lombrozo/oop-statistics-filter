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

# Set Java environment variables (optional)
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME/bin


# Set the working directory
WORKDIR /app

# Download the SourceMeter archive
RUN curl -LO https://github.com/sarnold/cccc/releases/download/3.2.0/cccc_3.2.0-1bookworm1_amd64.deb

# Unarchive the downloaded file
RUN sudo dpkg -i cccc_3.2.0-1bookworm1_amd64.deb

# Cleanup - remove the downloaded archive
RUN rm cccc_3.2.0-1bookworm1_amd64.deb

# Run the Jdepend Java analyzer
ENTRYPOINT [ \
"cccc", \
"--version" \
]
