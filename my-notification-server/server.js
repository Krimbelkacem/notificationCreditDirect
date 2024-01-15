const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");
var cookieParser = require("cookie-parser");
const fs = require("fs");

const app = express();

const http = require("http");

app.use(cookieParser());

app.use(cors());

app.use(bodyParser.urlencoded({ extended: true }));
const server = http.createServer(app);

const io = require("socket.io")(server, {
  cors: {
    origin: "http://localhost:4200",
    methods: ["GET", "POST"],
    credentials: true,
  },
});
const directors = {};
const courtiers = {};
// Dictionary to store pending messages for each receiver
const pendingMessages = {};

io.on("connection", (socket) => {
  socket.on("directorConnected", (directorId) => {
    directors[directorId] = socket.id;
    console.log(
      `Director ${directorId} connected with socket ID: ${socket.id}`
    );

    // Send pending messages if any
    sendPendingMessages(directorId);
  });

  socket.on("courtierConnected", (courtierId) => {
    courtiers[courtierId] = socket.id;
    console.log(
      `Courtier ${courtierId} connected with socket ID: ${socket.id}`
    );

    // Send pending messages if any
    sendPendingMessages(courtierId);
  });

  socket.on("sendMessage", ({ senderId, receiverId, message }) => {
    const receiverSocketId = directors[receiverId] || courtiers[receiverId];
    console.log(
      `Received message: ${message} from ${senderId} to ${receiverId}`
    );

    if (receiverSocketId) {
      // Send the message along with receiverId to the client
      io.to(receiverSocketId).emit("messageReceived", {
        senderId,
        receiverId, // Include the receiverId property
        message,
      });
      console.log(`Message sent to ${receiverId}`);
    } else {
      console.log(`Receiver with ID ${receiverId} not found. Storing message.`);
      // Store the message for the offline receiver
      if (!pendingMessages[receiverId]) {
        pendingMessages[receiverId] = [];
      }
      pendingMessages[receiverId].push({ senderId, receiverId, message });
    }
  });
  // Handle socket disconnection
  socket.on("disconnect", () => {
    // Find and remove the disconnected director or courtier from the collections
    const disconnectedDirector = Object.keys(directors).find(
      (key) => directors[key] === socket.id
    );
    const disconnectedCourtier = Object.keys(courtiers).find(
      (key) => courtiers[key] === socket.id
    );

    if (disconnectedDirector) {
      delete directors[disconnectedDirector];
      console.log(`Director ${disconnectedDirector} disconnected`);
    } else if (disconnectedCourtier) {
      delete courtiers[disconnectedCourtier];
      console.log(`Courtier ${disconnectedCourtier} disconnected`);
    }

    // Additional cleanup or actions can be added as needed
  });

  // Additional events and logic can be added as needed
});

app.use(cors());

server.listen(9000, () => {
  console.log("ServerIO is running at port 9000");
});

function sendPendingMessages(receiverId) {
  const receiverSocketId = directors[receiverId] || courtiers[receiverId];
  if (receiverSocketId && pendingMessages[receiverId]) {
    // Send pending messages to the connected receiver
    pendingMessages[receiverId].forEach(({ senderId, message, receiverId }) => {
      io.to(receiverSocketId).emit("messageReceived", {
        senderId,
        receiverId, // Include the receiverId property
        message,
      });
      console.log(`Pending message sent to ${receiverId}: ${message}`);
    });

    // Clear the pending messages for the receiver
    delete pendingMessages[receiverId];
  }
}
