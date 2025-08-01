import axios from "axios";

// ✅ Create an Axios instance with your backend base URL
const api = axios.create({
  baseURL: "http://localhost:8080/api", // adjust if your backend URL changes
  headers: {
    "Content-Type": "application/json",
  },
});

// ✅ Attach JWT token automatically (if it exists in localStorage)
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

// ✅ Optionally, handle global errors (like 401)
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      // For example, redirect to login on unauthorized
      console.warn("Unauthorized, redirecting to login...");
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

export default api;
