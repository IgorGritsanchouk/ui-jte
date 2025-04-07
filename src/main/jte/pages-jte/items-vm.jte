@param com.ui.model.CurrentPage currentPage
@param com.ui.result.PaginatedResult itemPaginatedResult
@param com.ui.util.InterMessage messages

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
    <h2 class="text-base font-semibold leading-7 text-gray-900">&nbsp;&nbsp; Items available to order</h2>
    <p class="mt-1 text-sm leading-6 text-gray-500">&nbsp;&nbsp;Click link to move to the next page</p>

    <div class="bg-white shadow-sm rounded-lg overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
            <tr>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Item id</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Description</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Price</th>
            </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">

              @for(var item: itemPaginatedResult.getItems())
                 <tr class="hover:bg-gray-50">
                     <td class="px-6 py-4 text-sm text-gray-500">${item.getItemId()}</td>
                     <td class="px-6 py-4 text-sm text-gray-500">${item.getName()}</td>
                     <td class="px-6 py-4 text-sm text-gray-500">${item.getDescription()}</td>
                     <td class="px-6 py-4 text-sm text-gray-500">${item.getPrice()}</td>
                 </tr>
              @endfor

              <div class="px-6 py-4 text-sm text-gray-500">
                  <span>page: ${itemPaginatedResult.getCurrentPage()}</span>
                  <span>of ${itemPaginatedResult.getTotalPages()} &nbsp;&nbsp;</span>

                  @if(itemPaginatedResult.getCurrentPage() > 1)
                  <a href="/items-vm?page=${(itemPaginatedResult.getCurrentPage() - 1)}&size=${itemPaginatedResult.getPageSize()}" class="text-blue-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Previous</a>
                  @endif

                  @if(itemPaginatedResult.getCurrentPage() < itemPaginatedResult.getTotalPages())
                  <a href="/items-vm?page=${(itemPaginatedResult.getCurrentPage() + 1)}&size=${itemPaginatedResult.getPageSize()}" class="text-blue-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Next</a>
                  @endif
              </div>

            </tbody>
        </table>
    </div>

</div>
</body>
</html>



