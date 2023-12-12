# Build And Run Vault Docker Container
- Add token and address to .env file
- Run `docker compose build`
- Run `docker compose up -d`
- Run `docker exec -it vault /bin/bash /secrets.sh` to add secrets

# Check /config Endpoint
- You should have jq installed on your system `brew install jq`
- jq is lightweight and flexible command-line JSON processor
- Run: `curl localhost:8888/config/<service-name>/default -vvv |jq`
- Example: `curl localhost:8888/config/product-service/default -vvv |jq`