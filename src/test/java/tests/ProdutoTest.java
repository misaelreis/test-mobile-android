package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import telas.LoginTela;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@DisplayName("Teste mobile - modulo de produtos")
public class ProdutoTest {
    private WebDriver app;
    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capacidades = new DesiredCapabilities();
        capacidades.setCapability("deviceName", "Pixel 2 API 30");
        capacidades.setCapability("platform", "Android");
        capacidades.setCapability("udid","emulator-5554");
        capacidades.setCapability("appPackage", "com.lojinha");
        capacidades.setCapability("appActivity", "com.lojinha.ui.MainActivity");
        capacidades.setCapability("app", "C:\\Users\\zaela\\Documents\\Estudos\\automacao-front-web\\" +
                "automation-mobile-android\\src\\main\\resources\\lojinha-nativa.apk");

        this.app = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidades);
        this.app.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void after(){
        app.quit();
    }

    @DisplayName("Validação de valor do produto não permitido")
    @Test
    public void testValidacaoDeValorNaoPermitido() {
        String mensagemApresentada = new LoginTela(app)
                .preencherUsuario("admin")
                .preencherSenha("admin")
                .acessarApp()
                .acessarFormulario()
                .preencherNomeProduto("Test")
                .preencherCorProduto("Verde")
                .preencherValorProduto("7.0000,01")
                .submeterFormularioErro()
                .validaMensagem();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",
                mensagemApresentada);
    }
}
