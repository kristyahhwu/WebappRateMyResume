# ==== CONFIGURE =====
# Use a Node 16 base image
FROM node:16-alpine AS build
# Set the working directory to /app inside the container
WORKDIR /app
# Copy app files
COPY . .

# ==== BUILD =====

RUN npm config set legacy-peer-deps true

# Install dependencies (npm ci makes sure the exact versions in the lockfile gets installed)
RUN npm ci 

# Build the app
RUN npm run build

# ==== RUN =======
FROM node:16-alpine
WORKDIR /app
COPY --from=build /app/build ./build

EXPOSE 3000
CMD [ "npx", "serve", "build" ]

# # Set the env to "production"
# ENV NODE_ENV production
# # Expose the port on which the app will be running (3000 is the default that `serve` uses)
# EXPOSE 3000
# # Start the app
# CMD [ "npx", "serve", "build" ]