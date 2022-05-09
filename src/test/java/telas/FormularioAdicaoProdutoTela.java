package telas;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAdicaoProdutoTela extends BaseTela {
    public FormularioAdicaoProdutoTela (WebDriver app){
        super(app);
    }
    public FormularioAdicaoProdutoTela preencherNomeProduto(String nome){
        app.findElement(By.id("com.lojinha:id/productName")).click();
        app.findElement(By.id("com.lojinha:id/productName"))
                .findElement(By.id("com.lojinha:id/editText")).sendKeys(nome);
        return this;
    }

    public FormularioAdicaoProdutoTela preencherValorProduto(String valor){
        app.findElement(By.id("com.lojinha:id/productValue")).click();
        app.findElement(By.id("com.lojinha:id/productValue"))
                .findElement(By.id("com.lojinha:id/editText")).sendKeys(valor);
        return this;
    }

    public FormularioAdicaoProdutoTela preencherCorProduto(String cor){
        app.findElement(By.id("com.lojinha:id/productColors")).click();
        app.findElement(By.id("com.lojinha:id/productColors"))
                .findElement(By.id("com.lojinha:id/editText")).sendKeys(cor);
        return this;
    }

    public FormularioAdicaoProdutoTela submeterFormularioErro(){
        app.findElement(By.id("com.lojinha:id/button")).click();
        return this;
    }

    public String validaMensagem(){
        return capturarToast();
    }
}
