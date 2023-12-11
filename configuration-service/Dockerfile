FROM hashicorp/vault:latest

COPY secrets.sh /secrets.sh

# Install the Vault CLI
RUN apk add --no-cache \
    bash \
    ca-certificates \
    jq

