package br.com.curso.productapi.modules.category.repository;

import br.com.curso.productapi.modules.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    //IgnoreCase = ignora maiscula ou miniscula
    //Containing = aplica o LIKE na consulta %description%

    List<Category> findByDescriptionIgnoreCaseContaining(String description);

}
