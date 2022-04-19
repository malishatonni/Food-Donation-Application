using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace food_donation_api.Migrations
{
    public partial class locationtableadded : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<Guid>(
                name: "GoogleLocationLocationId",
                table: "Organization",
                type: "uniqueidentifier",
                nullable: true);

            migrationBuilder.CreateTable(
                name: "Location",
                columns: table => new
                {
                    LocationId = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Lat = table.Column<double>(type: "float", nullable: false),
                    Long = table.Column<double>(type: "float", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Location", x => x.LocationId);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Organization_GoogleLocationLocationId",
                table: "Organization",
                column: "GoogleLocationLocationId");

            migrationBuilder.AddForeignKey(
                name: "FK_Organization_Location_GoogleLocationLocationId",
                table: "Organization",
                column: "GoogleLocationLocationId",
                principalTable: "Location",
                principalColumn: "LocationId",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Organization_Location_GoogleLocationLocationId",
                table: "Organization");

            migrationBuilder.DropTable(
                name: "Location");

            migrationBuilder.DropIndex(
                name: "IX_Organization_GoogleLocationLocationId",
                table: "Organization");

            migrationBuilder.DropColumn(
                name: "GoogleLocationLocationId",
                table: "Organization");
        }
    }
}
