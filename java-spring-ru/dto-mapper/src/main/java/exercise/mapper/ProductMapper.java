package exercise.mapper;

// BEGIN
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import exercise.dto.ProductUpdateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.model.Product;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {
    @Mapping(source = "price", target = "cost")
    @Mapping(source = "title", target = "name")
    @Mapping(source = "vendorCode", target = "barcode")
    public abstract Product map(ProductCreateDTO model);

    @Mapping(source = "cost", target = "price")
    @Mapping(source = "name", target = "title")
    @Mapping( source = "barcode", target = "vendorCode")
    public abstract ProductDTO map(Product model);

    @Mapping(source = "price", target = "cost")
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product model);
}
// END