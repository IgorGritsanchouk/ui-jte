@import com.ui.util.FINAL
@import org.springframework.validation.BindingResult
@param java.util.Map<String, String> countries
@param com.ui.model.User user
@param com.ui.model.Customer customer
@param com.ui.model.Order order
@param com.ui.model.OrderForm orderForm
@param com.ui.model.Employee employee
@param com.ui.model.EmployeeForm employeeForm
@param java.util.List<com.ui.model.EmployeeCustomerOrder> ecOrdersLst
@param com.ui.result.PaginatedResult itemPaginatedResult
@param com.ui.result.EmployeeCustomerOrderResult ecOrderPaginationResult
@param com.ui.util.InterMessage messages
@param com.ui.model.CurrentPage currentPage
@param BindingResult bindingResult

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Spring Boot App</title>
    <!--  <script src="https://cdn.tailwindcss.com"></script>  -->
    <link href="main.css" rel="stylesheet" />
</head>
<body class="bg-gray-100">
<div class="min-h-full">
    <!-- Navigation -->
    <nav class="bg-gray-800">
        <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
            <div class="flex h-16 items-center justify-between">
                <div class="flex items-center">
                    <div class="flex-shrink-0">
                        <img class="h-8 w-8" src="/mark.svg?color=indigo&shade=500" alt="Your Company">
                    </div>
                    <div class="hidden md:block">
                        <div class="ml-10 flex items-baseline space-x-4">
                            <a href="/home-vm" class="bg-gray-900 text-white rounded-md px-3 py-2 text-sm font-medium" aria-current="page">${messages.getMessage("dashboard.label")}</a>
                            <a href="/employee-form-vm" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">${messages.getMessage("employee.form.label")}</a>
                            <a href="/customer-form-vm" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">${messages.getMessage("customer.form.label")}</a>
                            <a href="/order-form-vm" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">${messages.getMessage("menu.order.label")}</a>
                            <a href="/ec-orders-vm" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">${messages.getMessage("menu.orders.label")}</a>
                            <a href="/api/v1/products" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Mongo Products</a>
                            <a href="/actuator" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Actuator</a>

                            <form action="/change-language" method="post">
                                <select name="language" id="language" onchange="this.form.submit()">
                                    <option value="en" selected="${currentPage.isSelected(FINAL.LANGUAGE_EN)}" >English</option>
                                    <option value="fr" selected="${currentPage.isSelected(FINAL.LANGUAGE_FR)}" >Français</option>
                                    <option value="es" selected="${currentPage.isSelected(FINAL.LANGUAGE_ES)}" >Español</option>
                                    <option value="de" selected="${currentPage.isSelected(FINAL.LANGUAGE_DE)}" >Deutsch</option>
                                    <option value="it" selected="${currentPage.isSelected(FINAL.LANGUAGE_IT)}" >Italiano</option>
                                </select>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </nav>

    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Collapsible Sidebar</title>
        <script src="js/alpine.min.js" defer></script>
        <!--       <script src="https://cdn.jsdelivr.net/npm/alpinejs@3.13.5/dist/cdn.min.js" defer></script>  -->
        <!--       <script src="https://cdn.tailwindcss.com"></script>  -->
        <link href="main.css" rel="stylesheet" />
    </head>
    <body class="bg-gray-100">

    <div x-data="{ open: true }" class="flex">
        <!-- Sidebar -->
        <nav :class="open ? 'w-44' : 'w-16'" class="bg-gray-800 min-h-screen transition-all duration-700 flex flex-col p-4">
 <!--       <nav :class="open ? 'w-44' : 'w-16'" class="bg-gray-800 h-screen transition-all duration-700 flex flex-col p-4">  -->
            <!-- Toggle Button -->
            <button @click="open = !open" class="text-white mb-4 p-2 rounded bg-gray-700">
                <span x-show="open">⬅ ${messages.getMessage("menu.collapse.label")}</span>
                <span x-show="!open">➡</span>
            </button>

            <!-- Navigation Links -->
            <a href="/home-vm" class="text-white flex items-center py-2 px-2 hover:bg-gray-700 rounded">
                <span class="mr-2">🏠</span> <span x-show="open">${messages.getMessage("home.label")}</span>
            </a>
            <a href="#" class="text-white flex items-center py-2 px-2 hover:bg-gray-700 rounded">
                <span class="mr-2">ℹ️</span> <span x-show="open">${messages.getMessage("about.label")}</span>
            </a>
            <a href="#" class="text-white flex items-center py-2 px-2 hover:bg-gray-700 rounded">
                <span class="mr-2">💼</span> <span x-show="open">${messages.getMessage("services.label")}</span>
            </a>
            <a href="#" class="text-white flex items-center py-2 px-2 hover:bg-gray-700 rounded">
                <span class="mr-2">📞</span> <span x-show="open">${messages.getMessage("contact.label")}</span>
            </a>
            <a href="/employee-form-vm" class="text-white flex items-center py-2 px-2 hover:bg-gray-700 rounded">
                <span class="mr-2">😊</span> <span x-show="open">${messages.getMessage("employee.form.label")}</span>
            </a>
            <a href="/customer-form-vm" class="text-white flex items-center py-2 px-2 hover:bg-gray-700 rounded">
                <span class="mr-2">👨‍💻</span> <span x-show="open">${messages.getMessage("customer.form.label")}</span>
            </a>
            <a href="/order-form-vm" class="text-white flex items-center py-2 px-2 hover:bg-gray-700 rounded">
                <span class="mr-2">🎁</span> <span x-show="open">${messages.getMessage("menu.order.label")}</span>
            </a>
            <a href="/ec-orders-vm" class="text-white flex items-center py-2 px-2 hover:bg-gray-700 rounded">
                <span class="mr-2">📭</span> <span x-show="open">${messages.getMessage("menu.orders.label")}</span>
            </a>
            <a href="/api/v1/products" class="text-white flex items-center py-2 px-2 hover:bg-gray-700 rounded">
                <span class="mr-2">🛒</span> <span x-show="open">Mongo Products</span>
            </a>
            <a href="/actuator" class="text-white flex items-center py-2 px-2 hover:bg-gray-700 rounded">
                <span class="mr-2">🏁</span> <span x-show="open">Actuator</span>
            </a>
        </nav>

        <!-- Master-vm content -->
        <master-vm>
<!--            <div class="bg-white shadow rounded-lg p-6">   -->
            <div class="bg-white shadow-sm rounded-lg overflow-hidden">

                @if (currentPage.getJteName() == "pages-jte/home-vm")
                    @template.pages-jte.home-vm(currentPage= currentPage, messages= messages)
                @endif
                @if (currentPage.getJteName() == "pages-jte/employee-form-vm")
                    @template.pages-jte.employee-form-vm(messages= messages, employeeForm= employeeForm, employee= employee, currentPage= currentPage, bindingResult=bindingResult)
                @endif
                @if (currentPage.getJteName() == "pages-jte/customer-form-vm")
                    @template.pages-jte.customer-form-vm(messages= messages, customer= customer, countries= countries, currentPage= currentPage, bindingResult=bindingResult)
                @endif
                @if (currentPage.getJteName() == "pages-jte/order-form-vm")
                    @template.pages-jte.order-form-vm(messages= messages, orderForm= orderForm, order= order, currentPage= currentPage, bindingResult=bindingResult)
                @endif
                @if (currentPage.getJteName() == "pages-jte/ec-orders-vm")
                    @template.pages-jte.ec-orders-vm(messages= messages, ecOrderPaginationResult= ecOrderPaginationResult, currentPage= currentPage)
                @endif
                @if (currentPage.getJteName() == "pages-jte/items-vm")
                    @template.pages-jte.items-vm(messages= messages, itemPaginatedResult= itemPaginatedResult, currentPage= currentPage)
                @endif

            </div>
        </master-vm>

    </div>
    </body>
    </html>

</div>
</body>
</html>

