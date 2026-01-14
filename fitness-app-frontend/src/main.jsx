import React from "react";
import ReactDOM from "react-dom/client";

import { Provider } from "react-redux";
import { store } from "./store/store";

import App from "./App";
import { AuthProvider } from "react-oauth2-code-pkce";
import { authConfig } from "./authConfig";

/* Loading UI while auth initializes */
const LoadingScreen = () => (
  <div
    style={{
      height: "100vh",
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      fontSize: "1.1rem",
      fontWeight: 500,
      color: "#4caf50",
    }}
  >
    Initializing authentication…
  </div>
);

// React 18 root
const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <React.StrictMode>
    <AuthProvider
      authConfig={authConfig}
      loadingComponent={<LoadingScreen />}
    >
      <Provider store={store}>
        <App />
      </Provider>
    </AuthProvider>
  </React.StrictMode>
);
