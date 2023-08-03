# Use a base image with Linux and necessary tools
FROM ubuntu:latest

RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Download the SourceMeter archive
RUN curl -LO https://github.com/sarnold/cccc/releases/download/3.2.0/cccc_3.2.0-1bookworm1_amd64.deb

# Unarchive the downloaded file
RUN dpkg -i cccc_3.2.0-1bookworm1_amd64.deb

COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

# Run the Jdepend Java analyzer
ENTRYPOINT ["./entrypoint.sh"]