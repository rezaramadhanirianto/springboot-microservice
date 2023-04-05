# SpringBoot E-Commerce

## Docker
- docker pull <code>mongo(image):latest(version)</code>
- docker run -d -p <code>27017:27017(port)</code> --name=<code>global-mongo(containerName) <code>mysql:5.7 (image:version)</code></code>
- docker rmi <code>5069d65bcc55(imageId)</code>

## Port
- lsof -i :8080 (Find app that listen port 8080)
- kill -9 7286 <code>PID</code>