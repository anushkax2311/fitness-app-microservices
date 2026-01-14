import { 
  Box, 
  Button, 
  Typography, 
  AppBar, 
  Toolbar, 
  Container, 
  Paper 
} from "@mui/material";
import { useContext, useEffect, useState } from "react";
import { AuthContext } from "react-oauth2-code-pkce";
import { useDispatch } from "react-redux";
import { BrowserRouter as Router, Navigate, Route, Routes } from "react-router";
import { setCredentials } from "./store/authSlice";
import ActivityForm from "./components/ActivityForm";
import ActivityList from "./components/ActivityList";
import ActivityDetail from "./components/ActivityDetail";

const ActivitiesPage = () => {
  return (
    <Paper elevation={3} sx={{ p: 3, mt: 3, borderRadius: 2 }}>
      <ActivityForm onActivitiesAdded={() => window.location.reload()} />
      <ActivityList />
    </Paper>
  );
};

function App() {
  const { token, tokenData, logIn, logOut } = useContext(AuthContext);
  const dispatch = useDispatch();

  useEffect(() => {
    if (token) {
      dispatch(setCredentials({ token, user: tokenData }));
    }
  }, [token, tokenData, dispatch]);

  return (
    <Router>
      {/* Top App Bar */}
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" sx={{ flexGrow: 1 }}>
            Fitness Tracker
          </Typography>
          {token && (
            <Button color="inherit" onClick={logOut}>
              Logout
            </Button>
          )}
        </Toolbar>
      </AppBar>

      {!token ? (
        /* Login Screen */
        <Box
          sx={{
            height: "calc(100vh - 64px)",
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            justifyContent: "center",
            textAlign: "center",
          }}
        >
          <Typography variant="h4" gutterBottom>
            Welcome to Fitness Tracker
          </Typography>
          <Typography variant="subtitle1" sx={{ mb: 3 }}>
            Track your activities and stay consistent 💪
          </Typography>
          <Button
            variant="contained"
            size="large"
            onClick={logIn}
          >
            Login
          </Button>
        </Box>
      ) : (
        /* Authenticated Area */
        <Container maxWidth="md">
          <Routes>
            <Route path="/activities" element={<ActivitiesPage />} />
            <Route path="/activities/:id" element={<ActivityDetail />} />
            <Route
              path="/"
              element={<Navigate to="/activities" replace />}
            />
          </Routes>
        </Container>
      )}
    </Router>
  );
}

export default App;
