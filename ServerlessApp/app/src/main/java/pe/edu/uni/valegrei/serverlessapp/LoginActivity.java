package pe.edu.uni.valegrei.serverlessapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ibm.cloud.appid.android.api.AppID;
import com.ibm.cloud.appid.android.api.AuthorizationException;
import com.ibm.cloud.appid.android.api.AuthorizationListener;
import com.ibm.cloud.appid.android.api.LoginWidget;
import com.ibm.cloud.appid.android.api.tokens.AccessToken;
import com.ibm.cloud.appid.android.api.tokens.IdentityToken;
import com.ibm.cloud.appid.android.api.tokens.RefreshToken;

import pe.edu.uni.valegrei.serverlessapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private AppID appId;
    private TokensManager tokensManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appId = AppID.getInstance();
        appId.initialize(this, getString(R.string.authTenantId), AppID.REGION_US_SOUTH);

        tokensManager = new TokensManager(this);

        binding.btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String storedAccessToken = tokensManager.getAccessToken();
        LoginWidget loginWidget = appId.getLoginWidget();
        loginWidget.launch(this, authorizationListener, storedAccessToken);
    }

    AuthorizationListener authorizationListener = new AuthorizationListener() {
        @Override
        public void onAuthorizationCanceled() {

        }

        @Override
        public void onAuthorizationFailure(AuthorizationException exception) {

        }

        @Override
        public void onAuthorizationSuccess(AccessToken accessToken, IdentityToken identityToken, RefreshToken refreshToken) {
            saveTokensAndGoMain(accessToken, identityToken, refreshToken);
        }
    };

    private void saveTokensAndGoMain(AccessToken accessToken, IdentityToken identityToken, RefreshToken refreshToken) {
        tokensManager.saveAccessToken(accessToken.getRaw());
        if (refreshToken != null)
            tokensManager.saveRefreshToken(refreshToken.getRaw());
        tokensManager.saveUserId(identityToken.getSubject());
        tokensManager.saveUserName(identityToken.getName());

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}