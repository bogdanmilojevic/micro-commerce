# How To Add Secrets
- Install HashiCorp vault:
`brew install vault`
- Run Vault server `vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"`
- Go to `http://localhost:8200`, use token from above for login
- Navigate to **Secret Engines**
- Click **Enable new engine**
- Select **KV**
- Click **Next**
- In 'Path' field type **dev**
- Click **Enable Engine**
- Run `bash secrets.sh` in terminal. This will add all secrets to your local vault server.