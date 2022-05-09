package testes;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import telas.LoginTela;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@DisplayName("Testes de login")
public class LoginTest {
    private WebDriver app;
    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capacidades = new DesiredCapabilities();
        capacidades.setCapability("deviceName", "Pixel 2 API 30");
        capacidades.setCapability("platform", "Android");
        capacidades.setCapability("udid","emulator-5554");
        capacidades.setCapability("appPackage", "com.lojinha");
        capacidades.setCapability("appActivity", "com.lojinha.ui.MainActivity");
        capacidades.setCapability("app", "C:\\src\\main\\resources\\lojinha-nativa.apk");

        this.app = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidades);
        this.app.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void after(){
        app.quit();
    }

    @DisplayName("Validação de login com sucesso")
    @Test
    public void testLoginSucesso (){
        new LoginTela(app)
                .preencherUsuario("admin")
                .preencherSenha("admin")
                .acessarApp()
                .validaAreaLogada();
    }

    @DisplayName("Validação de login sem usuário e senha")
    @Test
    public void testLoginSemDados(){
        String mensagemApresentada = new LoginTela(app)
                .acessarApp()
                .validaMensagem();
        Assertions.assertEquals("Erro de autenticação", mensagemApresentada);
    }

    @DisplayName("Validação de login sem usuário")
    @Test
    public void testLoginSemUsuario(){
        String mensagemApresentada = new LoginTela(app)
                .preencherSenha("admin")
                .acessarApp()
                .validaMensagem();
        Assertions.assertEquals("Erro de autenticação", mensagemApresentada);
    }

    @DisplayName("Validação de login sem senha")
    @Test
    public void testLoginSemSenha(){
        String mensagemApresentada = new LoginTela(app)
                .preencherUsuario("admin")
                .acessarApp()
                .validaMensagem();
        Assertions.assertEquals("Erro de autenticação", mensagemApresentada);
    }

    @DisplayName("Validação de login com dados incorretos")
    @Test
    public void testLoginDadosIncorretos(){
        String mensagemApresentada = new LoginTela(app)
                .preencherUsuario("test")
                .preencherSenha("test")
                .acessarApp()
                .validaMensagem();
        Assertions.assertEquals("Erro de autenticação", mensagemApresentada);
    }
}
