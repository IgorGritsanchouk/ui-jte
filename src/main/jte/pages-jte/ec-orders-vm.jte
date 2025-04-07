@import com.ui.util.InterMessage
@import com.ui.model.CurrentPage
@param InterMessage messages
@param CurrentPage currentPage
@param com.ui.result.EmployeeCustomerOrderResult ecOrderPaginationResult

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${currentPage.getTitle()}</title>
    <link href="main.css" rel="stylesheet" />
</head>

<body class="bg-gray-50">
<div class="max-w-7xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <h2 class="text-base font-semibold leading-7 text-gray-900">&nbsp;&nbsp;${messages.getMessage("orders.title.label")}</h2>
    <p class="mt-1 text-sm leading-6 text-gray-500">&nbsp;&nbsp;${messages.getMessage("orders.info.label")}</p>
    <p class="mt-1 text-sm leading-6 text-gray-500">&nbsp;</p>

<div class="bg-white shadow-sm rounded-lg overflow-x-auto">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Customer Company</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Customer Phone</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Order Date</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Tracking Num</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Emp Name</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Emp Phone</th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
        @for(var ecOrder : ecOrderPaginationResult.getOrders())
            <tr class="hover:bg-gray-50">
                <td class="px-6 py-4 text-sm text-gray-500">
                    <a href="/find-customer-vm?customerId=${ecOrder.getCustomerId()}" class="text-blue-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">${ecOrder.getCompanyName()}</a>
                </td>
                <td class="px-6 py-4 text-sm text-gray-500">${ecOrder.getPhone()}</td>

                <td class="px-6 py-4 text-sm text-gray-500">
                    <a href="/find-order-vm?orderId=${ecOrder.getOrderId()}" class="text-blue-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">${ecOrder.getOrderDate().toString()}</a>
                </td>

                <td class="px-6 py-4 text-sm text-gray-500">${ecOrder.getOrderStatus()}</td>
                <td class="px-6 py-4 text-sm text-gray-500">${ecOrder.getTrackingNumber()}</td>

                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                    <a href="/find-employee-vm?employeeId=${ecOrder.getEmployeeId()}" class="text-blue-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">${ecOrder.getFirstName()}&nbsp;${ecOrder.getLastName()}</a>
                </td>
                <td class="px-6 py-4 text-sm text-gray-500">${ecOrder.getMobilePhone()}</td>

                <!--
                <td class="px-6 py-4 whitespace-nowrap text-sm">
                    if
                        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                                        Completed
                                    </span>
                    else
                        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-yellow-100 text-yellow-800">
                                        Pending
                                    </span>
                    endif
                </td>
 -->
            </tr>
        @endfor

        <div class="px-6 py-4 text-sm text-gray-500">
            <span>page: ${ecOrderPaginationResult.getCurrentPage()}</span>
            <span>of ${ecOrderPaginationResult.getTotalPages()} &nbsp;&nbsp;</span>

            @if(ecOrderPaginationResult.getCurrentPage() > 1)
                <a href="/ec-orders-vm?page=${(ecOrderPaginationResult.getCurrentPage() - 1)}&size=${ecOrderPaginationResult.getPageSize()}" class="text-blue-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Previous</a>
            @endif

            @if(ecOrderPaginationResult.getCurrentPage() < ecOrderPaginationResult.getTotalPages())
                <a href="/ec-orders-vm?page=${(ecOrderPaginationResult.getCurrentPage() + 1)}&size=${ecOrderPaginationResult.getPageSize()}" class="text-blue-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Next</a>
            @endif
        </div>

        </tbody>
    </table>
</div>

</div>
</body>
</html>