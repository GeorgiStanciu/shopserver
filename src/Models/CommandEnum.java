package Models;

public enum CommandEnum {

	ViewProductsCommand,
	ViewCategoriesCommandByParent,
	ViewOrderesCommand,
	ViewUsersCommand,
	
	
	AddProductCommand,
	AddFavoriteCommand,
	AddOrderedCommand,
	AddReviewCommand,
	AddBasketCommand,
	AddUserCommand,
	AddProductToBasketCommand,
	
	UpdateProductCommand,
	UpdateFavoriteCommand,
	UpdateReviewCommand,
	UpdateBasketCommand,
	UpdateUserCommand,
	
	
	
	RemoveProductCommand,
	RemoveFavoriteCommand,
	RemoveReviewCommand,
	RemoveUserCommand,
	RemoveProductFromBasketCommand,
	
	GetProductCommand,
	GetProductByCategoryCommand,
	GetFavoritesByUserCommand,
	GetOrderesByUserCommand,
	GetReviewsByProductCommand,
	GetBasketByUserCommand,
	GetUserCommand,
	GetUserByFirebaseCommand,
	GetIsFavoriteProductCommand,
	
	EndConnectionCommand
	
	
}
