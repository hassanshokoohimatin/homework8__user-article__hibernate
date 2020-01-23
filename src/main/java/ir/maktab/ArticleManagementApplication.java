package ir.maktab;

import ir.maktab.entities.Article;
import ir.maktab.entities.Category;
import ir.maktab.entities.User;
import ir.maktab.features.articlemanagement.usecase.*;
import ir.maktab.features.articlemanagement.usecaseimpl.*;
import ir.maktab.features.categorymanagement.usecase.ListAllCategories;
import ir.maktab.features.categorymanagement.usecase.NewCategory;
import ir.maktab.features.categorymanagement.usecaseimpl.ListAllCategoriesImpl;
import ir.maktab.features.categorymanagement.usecaseimpl.NewCategoryImpl;
import ir.maktab.features.usermanagement.usecase.*;
import ir.maktab.features.usermanagement.usecaseimpl.*;
import ir.maktab.share.AuthenticationService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ArticleManagementApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String manner = "";
        while (!manner.equals("exit")) {
            System.out.println("Welcome...\nSignin\nSignup\nArticles\nsearch\nExit");
            manner = scanner.next();

            if (manner.equals("signin")) {

                User loginUser = AuthenticationService.getInstance().getLoginUser();
                if (loginUser == null) {
                    System.out.println("Enter username : ");
                    String username = scanner.next();
                    System.out.println("Enter password : ");
                    String password = scanner.next();
                    SignIn signIn = new SignInImpl();
                    loginUser = signIn.signIn(username, password);
                    AuthenticationService.getInstance().setLoginUser(loginUser);
                    if (loginUser == null) {
                        System.out.println("Wrong username or password...");
                    } else {
                        System.out.println("Signed in\n\n");
                        int choice = 0;
                        while (choice != 7) {
                            System.out.println("1.See your articles\n2.Edit your article\n3.Enter new article\n4.Change password\n5.Dashboard" +
                                    "\n6.Publish an Article\n7.Sign Out");
                            choice = scanner.nextInt();
                            if (choice == 1) {

                                SeeArticlesByUser seeArticlesByUser = new SeeArticlesByUserImpl();
                                List<Article> articles = seeArticlesByUser.articleList(loginUser.getId());
                                if (articles.size() == 0)
                                    System.out.println("You dont have any articles...");
                                else {
                                    for (Article a : articles) {
                                        System.out.println(a);
                                    }
                                }

                            }
                            //EDIT ARTICLE
                            if (choice == 2) {
                                SeeArticlesByUser seeArticlesByUser = new SeeArticlesByUserImpl();
                                List<Article> articles = seeArticlesByUser.articleList(loginUser.getId());
                                if (articles.size() == 0)
                                    System.out.println("You dont have any articles...");
                                else
                                    {
                                        for (Article a : articles) {
                                            System.out.println(a);
                                        }
                                    System.out.println("Enter the id of article you want to edit...");
                                    Long id = scanner.nextLong();
                                    ListArticleById listArticleById = new ListArticleByIdImpl();
                                    Article article = listArticleById.list(id);
                                    System.out.println("Enter new title or * to skip : ");
                                    String title = scanner.next();
                                    if (! title.equals("*"))
                                        article.setTitle(title);
                                    System.out.printf("%s","Enter new brief or * to skip : ");
                                    String brief = scanner.next();
                                    if (! brief.equals("*"))
                                        article.setBrief(brief);
                                    System.out.printf("%s","Enter new content or * to skip : ");
                                    String content = scanner.next();
                                    if (! content.equals("*"))
                                        article.setContent(content);
                                    System.out.println("Edit publishing situation...yes or no or * to skip");
                                    String isPublished = scanner.next();
                                    if (! isPublished.equals("*"))
                                        article.setIsPublished(isPublished);
                                    ListAllCategories listAllCategories = new ListAllCategoriesImpl();
                                    List<Category> categories = listAllCategories.categoryList();
                                    System.out.println("id\t\t\ttitle");
                                    for (Category c : categories) {
                                        System.out.printf("%d\t\t\t%s\n", c.getId(), c.getTitle());
                                    }
                                    System.out.println("Enter the id of category or type 0 to create one...");
                                    int categoryChoice = scanner.nextInt();
                                    if (categoryChoice == 0) {

                                        System.out.println("Enter title : ");
                                        String categoryTitle = scanner.next();
                                        System.out.println("Enter description : ");
                                        String categoryDescription = scanner.next();
                                        Category category = new Category(null, categoryTitle, categoryDescription, null);
                                        article.setCategory(category);
                                        NewCategory newCategory = new NewCategoryImpl();
                                        newCategory.createCategory(category);

                                    } else {
                                        article.setCategory(categories.get(categoryChoice - 1));
                                    }

                                    article.setLastUpdateDate(new Date().toString());

                                    EditArticle editArticle = new EditArticleImpl();
                                    editArticle.edit(article);
                                }
                            }

                            //ENTER NEW ARTICLE
                            if (choice == 3) {
                                Article article = new Article();
                                article.setId(null);
                                article.setUser(loginUser);
                                System.out.println("Enter title : ");
                                String title = scanner.next();
                                article.setTitle(title);
                                System.out.printf("%s","Enter brief : ");
                                String brief = scanner.next();
                                article.setBrief(brief);
                                System.out.printf("%s","Enter content : ");
                                String content = scanner.next();
                                article.setContent(content);
                                System.out.printf("%s","Enter create date : ");
                                String createDate = scanner.next();
                                article.setCreateDate(createDate);
                                System.out.println("Enter publish date : ");
                                String publishDate = scanner.next();
                                article.setPublishDate(publishDate);
                                article.setLastUpdateDate(new Date().toString());
                                article.setIsPublished("no");

                                ListAllCategories listAllCategories = new ListAllCategoriesImpl();
                                List<Category> categories = listAllCategories.categoryList();
                                System.out.println("id\t\t\ttitle");
                                for (Category c : categories) {
                                    System.out.printf("%d\t\t\t%s\n", c.getId(), c.getTitle());
                                }
                                System.out.println("Enter the id of category or type 0 to create one...");
                                int categoryChoice = scanner.nextInt();
                                if (categoryChoice == 0) {

                                    System.out.println("Enter title : ");
                                    String categoryTitle = scanner.next();
                                    System.out.println("Enter description : ");
                                    String categoryDescription = scanner.next();
                                    Category category = new Category(null, categoryTitle, categoryDescription, null);
                                    article.setCategory(category);
                                    NewCategory newCategory = new NewCategoryImpl();
                                    newCategory.createCategory(category);

                                } else {
                                    article.setCategory(categories.get(categoryChoice - 1));
                                }

                                EnterNewArticleByUser enterNewArticleByUser = new
                                        EnterNewArticleByUserImpl();
                                enterNewArticleByUser.enterArticle(article);

                            }

                            //CHANGE PASSWORD
                            if (choice == 4) {

                                System.out.println("Enter your old password : ");
                                String oldPassword = scanner.next();
                                if (loginUser.getPassword().equals(oldPassword)) {
                                    System.out.println("Enter new password");
                                    String newPassword = scanner.next();
                                    System.out.println("Reenter the password");
                                    String reenteredPassword = scanner.next();
                                    if (newPassword.equals(reenteredPassword)) {
                                        loginUser.setPassword(newPassword);
                                        ChangePassword changePassword = new ChangePasswordImpl();
                                        changePassword.changePassword(newPassword);
                                        System.out.println("password changed successfully...");
                                    }
                                    else{
                                        System.out.println("Entered passwords are not the same...try again");
                                    }
                                }
                                else {
                                    System.out.println("wrong password...you can not change it");
                                }
                            }
                            //DASHBOARD
                            if (choice == 5) {

                                System.out.println("choose a number to see  details...\n1.See number of all your articles\n" +
                                        "2.See number of all your published articles");
                                int dashboardChoice = scanner.nextInt();
                                if (dashboardChoice == 1) {

                                    SeeAllArticlesOfUser seeAllArticlesOfUser = new SeeAllArticlesOfUserImpl();

                                    List<Article> articleList = seeAllArticlesOfUser.articleList(AuthenticationService.getInstance()
                                            .getLoginUser().getId());
                                    System.out.printf("%s%d","number of your articles : ",articleList.size());
                                    System.out.println();

                                } else if (dashboardChoice == 2) {

                                    SeeAllPublishedArticlesOfUser seeAllPublishedArticlesOfUser =
                                            new SeeAllPublishedArticlesOfUserImpl();

                                    List<Article> articles = seeAllPublishedArticlesOfUser.articleList(AuthenticationService.getInstance()
                                            .getLoginUser().getId());
                                    System.out.printf("%s%d","number of your published articles : ",articles.size());
                                    System.out.println();
                                }
                            }

                            //PUBLISH AN ARTICLE
                            if (choice == 6) {
                                User user = AuthenticationService.getInstance().getLoginUser();
                                SeeAllArticlesOfUser seeAllArticlesOfUser = new SeeAllArticlesOfUserImpl();
                                List<Article> articles = seeAllArticlesOfUser.articleList(AuthenticationService.getInstance()
                                        .getLoginUser().getId());
                                if (articles.size()!=0) {
                                    for (Article a : articles) {
                                        System.out.println(a);
                                    }
                                    System.out.println("Which article do you want to change publishing? Enter the id");
                                    Long articleId = scanner.nextLong();
                                    ChangePublishing changePublishing = new ChangePublishingImpl();
                                    changePublishing.changePublishing(articleId, user);
                                    System.out.println();
                                }else
                                    System.out.println("You dont have any articles");
                            }
                            //SIGN OUT
                            if (choice == 7) {
                                SignOut signOut = new SignOutImpl();
                                signOut.signOut();
                            }
                        }
                    }

                } else {
                    System.out.printf("the user %s is logged in...getting sign out...\n", loginUser.getUsername());
                    SignOut signOut = new SignOutImpl();
                    signOut.signOut();
                    System.out.println("signed out successfully...");
                }
            }

            if (manner.equals("signup")) {
                User loginUser = AuthenticationService.getInstance().getLoginUser();
                if (loginUser == null) {
                    int existUserCount = 0;
                    System.out.println("Enter username : ");
                    String username = scanner.next();
                    System.out.println("Enter national code : ");
                    String nationalCode = scanner.next();
                    ListAllUsers listAllUsers = new ListAllUsersImpl();
                    List<User> userList = listAllUsers.userList();
                    for (User u : userList) {
                        if (u.getUsername().equals(username) || u.getNationalCode().equals(nationalCode)) {
                            existUserCount++;
                            break;
                        }
                    }
                    if (existUserCount == 0) {
                        System.out.println("Enter birthday : ");
                        String birthday = scanner.next();
                        User user = new User(null, username, nationalCode, birthday, nationalCode, null);
                        SignUp signUp = new SignUpImpl();
                        signUp.signUp(user);
                        System.out.println("signed up successfully...you can sign in now");
                    } else {
                        System.out.println("This username or password already exist...try another");
                    }

                } else {
                    System.out.printf("the user %s is logged in...", loginUser.getUsername());
                    SignOut signOut = new SignOutImpl();
                    signOut.signOut();
                    System.out.println("signed out successfully...");
                }
            }

            if (manner.equals("articles")) {
                ListArticlesByTitleBrief listArticlesByTitleBrief = new ListArticlesByTitleBriefImpl();
                List<Article> articleList = listArticlesByTitleBrief.articleList();
                if (articleList.size() == 0) {
                    System.out.println("there is no articles...");
                } else {
                    System.out.println("id\t\ttitle\t\tbrief");
                    for (Article a : articleList) {
                        System.out.printf("%d\t\t%s\t\t%s\n", a.getId(), a.getTitle(), a.getBrief());
                    }
                    System.out.println("enter the id of the article too see more detail or press 0 to back to main menu");
                    Long id = scanner.nextLong();
                    if (id != 0) {
                        for (Article a : articleList) {
                            if (id == a.getId()) {
                                ListArticleById listArticleById = new ListArticleByIdImpl();
                                System.out.println(listArticleById.list(id));
                                break;
                            }
                        }
                    }
                }
            }

            if (manner.equals("exit")) {
                System.out.println("END");
            }
            if (manner.equals("search")){
                System.out.println("1.search by name of author\n2.search by title of article");
                int choice = scanner.nextInt();
                if (choice==1){
                    System.out.println("enter the name of the author to be searched : ");
                    String name = scanner.next();
                    List<Article> articles = SearchByAuthorName.articles(name);
                    if (articles.size()==0)
                        System.out.println("sorry this user has no articles or maybe this is a wrong username");
                    else{
                        for (Article a : articles)
                            System.out.println(a);
                    }
                }
                if (choice==2){
                    System.out.println("enter the title of article to be searched : ");
                    String title = scanner.next();
                    List<Article> articles = SearchArticleByTitle.articles(title);
                    if (articles.size()==0)
                        System.out.println("sorry there is no articles with this title...");
                    else{
                        for (Article a : articles)
                            System.out.println(a);
                    }
                }
            }
        }
    }
}
