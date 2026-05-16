package com.app.vdc.demo.Controller;

import com.app.vdc.demo.services.Pagamento.PagamentoIS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private PagamentoIS pagamento;

	@RequestMapping(value = "/pix", method = RequestMethod.POST)
	public String pagamentoPix() {
		return "Pagamento via Pix";
	}

	@RequestMapping("/cartao-credito")
	public String pagamentoCartaoCredito() {
		return "Pagamento via Cartão de Crédito";
	}

	@PostMapping("/cartao-debito")
	public String pagamentoCartaoDebito(@RequestParam String idDebito) {
		this.pagamento.sendPaymentRegister(idDebito);
		return "Pagamento via Cartão de Debito";
	}

	@RequestMapping("/boleto")
	public String pagamentoBoleto() {
		return "Pagamento via Boleto";
	}

}
