//package com.ekan.model.jpa;
//
//import java.util.List;
//
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ApplicationContext;
//
//import com.ekan.EkanApiApplication;
//import com.ekan.model.Beneficiario;
//import com.ekan.repository.BeneficiarioRepository;
//
//public class ConsultaBeneficiarioMain {
//    
//    public static void main (String [] args) {
//        ApplicationContext applicationContext = new SpringApplicationBuilder(EkanApiApplication.class)
//                .web(WebApplicationType.NONE).run(args);
//        
//        BeneficiarioRepository beneficiarioRepository = applicationContext.getBean(BeneficiarioRepository.class);
//        List<Beneficiario> beneficiarios = beneficiarioRepository.listarTodos();
//        
//        for(Beneficiario beneficiario : beneficiarios) {
//            System.out.println(beneficiario.getNome());
//        } 
//        
//        Beneficiario beneficiario = new Beneficiario();
//        beneficiario.setNome("Kaic");
//        Beneficiario retorno = beneficiarioRepository.salvar(beneficiario);
//        System.out.println("Adicionado beneficiario : " + retorno);
//        
//        Beneficiario retornoBusca = beneficiarioRepository.buscarPorId(retorno.getId());
//        System.out.println("feito busca pelo id" + retornoBusca);
//        
//        // faço alteração utilizando o mesmo metodo de adicionar
//        retornoBusca.setNome("Sarah");
//        Beneficiario retornoAlteracao =  beneficiarioRepository.salvar(retornoBusca);
//        System.out.println("Feito alteração do beneficiario: " + retorno.getNome() + " para: " + retornoAlteracao.getNome() );
//        
//        beneficiarioRepository.remover(retornoAlteracao.getId());
//    }    
//    
//}