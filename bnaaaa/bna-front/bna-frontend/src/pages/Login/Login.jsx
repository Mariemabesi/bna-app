import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import VirtualKeyboard from "../../components/VirtualKeyboard";

export default function Login() {
  const [isLogin, setIsLogin] = useState(true);
  const [username, setUsername] = useState("");
  const [showKeyboard, setShowKeyboard] = useState(false);
  const [virtualPassword, setVirtualPassword] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    // ✅ If token already exists, redirect
    const token = localStorage.getItem("token");
    if (token) {
      navigate("/dashboard");
    }
  }, [navigate]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const endpoint = isLogin
      ? "http://localhost:8080/api/users/auth/login"
      : "http://localhost:8080/api/users/auth/signup";

    const payload = {
      username: username,
      password: virtualPassword,
    };

    try {
      const res = await fetch(endpoint, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      const data = await res.json();
      if (res.ok) {
        // ✅ store the token
        localStorage.setItem("token", data.token);
        // if your backend doesn't return user info, you can skip this line:
        localStorage.setItem("user", JSON.stringify({ username }));
        // ✅ redirect with React Router
        navigate("/dashboard");
      } else {
        alert(data.error || "Erreur d'authentification");
      }
    } catch (err) {
      console.error(err);
      alert("Erreur de connexion au serveur");
    }
  };

  return (
    <div className="bna-bg">
      <div className="bna-header">
        <img src="/bnalogo.png" alt="BNA Logo" className="bna-logo" />
        <h2>BNA eBanking</h2>
        <p className="bna-sub">
          {isLogin
            ? "Bienvenue dans votre espace BNA-eBanking"
            : "Créer votre accès sécurisé BNA"}
        </p>
      </div>

      <form className="bna-form" onSubmit={handleSubmit}>
        <h3>Accès sécurisé</h3>
        <input
          type="text"
          placeholder="Identifiant"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Mot de passe"
          value={virtualPassword}
          onFocus={() => setShowKeyboard(true)}
          readOnly
          required
        />
        <div className="bna-buttons">
          <button type="reset" className="btn-corriger">
            CORRIGER
          </button>
          <button type="submit" className="btn-valider">
            VALIDER
          </button>
        </div>
        <div className="bna-footer">
          <a href="#">Mot de passe oublié ?</a>
          <div className="assist">
            <span>Assistance Commerciale</span>
            <span>Aide</span>
          </div>
        </div>
        <div style={{ marginTop: "20px" }}>
          <button
            type="button"
            className="btn-switch"
            onClick={() => {
              setIsLogin(!isLogin);
              setVirtualPassword("");
            }}
          >
            {isLogin
              ? "Créer un nouveau compte"
              : "Déjà inscrit ? Se connecter"}
          </button>
        </div>
      </form>

      {showKeyboard && (
        <VirtualKeyboard
          onClose={() => {
            setShowKeyboard(false);
            setVirtualPassword("");
          }}
          onDigit={(digit) => setVirtualPassword((prev) => prev + digit)}
          onSubmit={() => setShowKeyboard(false)}
        />
      )}
    </div>
  );
}
